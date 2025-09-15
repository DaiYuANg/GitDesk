package org.git.desk.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.git.desk.model.Account;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

@Singleton
public class AccountKeystore {

  private static final String KEYSTORE_FILE = "accounts.jceks";
  private static final char[] STORE_PASSWORD = "changeit".toCharArray(); // 建议运行时输入
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private @NotNull KeyStore loadKeystore() throws Exception {
    KeyStore ks = KeyStore.getInstance("JCEKS");
    File file = new File(KEYSTORE_FILE);
    if (file.exists()) {
      try (FileInputStream fis = new FileInputStream(file)) {
        ks.load(fis, STORE_PASSWORD);
      }
    } else {
      ks.load(null, STORE_PASSWORD);
    }
    return ks;
  }

  public void saveAccount(Account account) throws Exception {
    val ks = loadKeystore();

    // 1. 转 JSON
    val json = MAPPER.writeValueAsString(account);

    // 2. 包装成 SecretKey
    val secretKey = new SecretKeySpec(json.getBytes(), "AES");

    // 3. 存入 keystore
    val entry = new KeyStore.SecretKeyEntry(secretKey);
    val protection = new KeyStore.PasswordProtection(STORE_PASSWORD);
    ks.setEntry(account.getPlatform(), entry, protection);

    // 4. 保存
    @Cleanup val fos = new FileOutputStream(KEYSTORE_FILE);
    ks.store(fos, STORE_PASSWORD);
  }

  public @Nullable Account loadAccount(String platform) throws Exception {
    val ks = loadKeystore();
    val protection = new KeyStore.PasswordProtection(STORE_PASSWORD);

    KeyStore.SecretKeyEntry entry = (KeyStore.SecretKeyEntry) ks.getEntry(platform, protection);
    if (entry != null) {
      String json = new String(entry.getSecretKey().getEncoded());
      return MAPPER.readValue(json, Account.class);
    }
    return null;
  }
}