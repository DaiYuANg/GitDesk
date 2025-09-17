
module org.git.desk {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  requires org.jetbrains.annotations;
  requires java.compiler;
  requires org.slf4j;
  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires io.avaje.inject;
  requires static lombok;
  requires com.fasterxml.jackson.databind;
  requires atlantafx.base;
  requires com.techsenger.stagepro.core;
  requires com.techsenger.toolkit.fx;
  requires com.jthemedetector;
  requires dev.dirs;
  requires org.apache.commons.io;
  requires com.google.common;
  requires io.soabase.recordbuilder.core;
  requires org.kohsuke.github.api;
  requires io.ebean.api;
  requires io.ebean.querybean;
  requires org.apache.commons.lang3;
  requires io.smallrye.mutiny;
  requires javafx.base;
  requires com.dlsc.preferencesfx;
  requires it.unimi.dsi.fastutil;
  requires java.prefs;

  opens org.git.desk to javafx.fxml;
  exports org.git.desk;
  opens org.git.desk.controller to javafx.fxml;
  exports org.git.desk.controller;

  provides io.avaje.inject.spi.InjectExtension with org.git.desk.DeskModule;
  provides io.ebean.config.LookupProvider with org.git.desk.lookup.EbeanMethodLookup;
  provides io.ebean.config.EntityClassRegister with org.git.desk.entity.EbeanEntityRegister;
}