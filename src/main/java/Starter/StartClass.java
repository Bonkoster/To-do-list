package Starter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.javafx.application.LauncherImpl;

import DAO.MemoryPartDAOImpl;
import Entityes.MemoryPart;
import GUI.MemoryListGUI;
import javafx.application.Application;

public class StartClass {
	
	public static void main(String [] args){

		Application.launch(MemoryListGUI.class, args);
		
	}
}
