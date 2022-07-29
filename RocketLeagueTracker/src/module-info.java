module rocketLeagueTracker {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.base;
	requires javafx.fxml;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires java.persistence;
	exports main;
	exports controller;
	exports classes;
	exports database;
	opens main to javafx.fxml;
	opens controller to javafx.fxml;
	opens database to javafx.fxml;
	opens classes to org.hibernate.orm.core;
}