package com.example.cst438project1;

import android.content.Context;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class AccountLogTest {

        @Test
        public void useAppContext() {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

            assertEquals("com.example.cst438project1", appContext.getPackageName());
        }

        private AccountLogDAO accountLogDAO;
        private AppDatabase db;


        @Before
        public void createDB() {
            db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();

            accountLogDAO = db.getAccountLogDAO();
        }

        @Before
        public void setUp() throws Exception {
            createDB();
        }

        @After
        public void tearDown() throws Exception {

        }


        @Test
        public void insertCourse() {
            AccountLog testAccount = new AccountLog("Test","Run","UserTest","PassTest");

            CourseLog testCourse = new CourseLog("Instructor", "CST101",
                    "Test Description", "1/1/1970", "1/1/2020");

            testAccount.insertCourse(testCourse);

            accountLogDAO.insert(testAccount);
            List<AccountLog> DBvals = accountLogDAO.getAccountLog();

            List<CourseLog> userTestCourses = DBvals.get(0).getUserCourses();

            assertEquals(testCourse.getDescription(),userTestCourses.get(0).getDescription());



        }


        @Test
        public void insert() {
            AccountLog testValue = new AccountLog("Test","Run","UserTest","PassTest");

            accountLogDAO.insert(testValue);
            List<AccountLog> DBvalue = accountLogDAO.getAccountLog();
            assertEquals(1, DBvalue.size());

            assertEquals(testValue.getFirstname(), DBvalue.get(0).getFirstname());
            assertEquals(testValue, DBvalue.get(0));
        }


        @Test
        public void update() {
            AccountLog testValue = new AccountLog("Test","Run","UserTest","PassTest");

            accountLogDAO.insert(testValue);

            List<AccountLog> DBValue = accountLogDAO.getAccountLog();
            testValue = DBValue.get(0);
            testValue.setFirstname("Test1");
            testValue.setUsername("UserTest1");
            accountLogDAO.update(testValue);

            List<AccountLog> DBValueUpdated = accountLogDAO.getAccountLog();
            AccountLog updated = DBValueUpdated.get(0);
            assertEquals(testValue.getUsername(), updated.getUsername());
        }



    }

