package com.example.cst438project1;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CourseTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.cst438project1", appContext.getPackageName());
    }

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;


    @Before
    public void createDB() {
        courseDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), CourseDatabase.class)
                .allowMainThreadQueries()
                .build();

        courseDao = courseDatabase.getCourseLogDAO();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /** Test case for insert()
     * 2/4/2020 Successfully passed test
     */
    @Test
    public void update() {
        CourseLog testValue = new CourseLog("Instructor", "CST101",
                "Test Description", "1/1/1970", "1/1/2020");

        courseDao.insert(testValue);

        List<CourseLog> DBValue = courseDao.getCourseLogs();
        testValue = DBValue.get(0);
        testValue.setInstructor("New Instructor");
        testValue.setTitle("CST336");
        courseDao.update(testValue);

        List<CourseLog> DBValueUpdated = courseDao.getCourseLogs();
        CourseLog updated = DBValueUpdated.get(0);
        assertEquals(testValue.getDescription(), updated.getDescription());
    }
}

