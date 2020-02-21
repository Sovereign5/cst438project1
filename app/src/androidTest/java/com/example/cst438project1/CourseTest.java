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
     * Test Fails: Line 73, assertEquals not equal
     * testValue, CourseID == 0
     * DBvalue.get(0), courseID == 1
     * This is likely because of the @autoGenerate = true over in CourseLog.java
     */
    @Test
    public void insert() {
        CourseLog testValue = new CourseLog("Instructor", "CST101",
                "Test Description", "1/1/1970", "1/1/2020");

        courseDao.insert(testValue);
        List<CourseLog> DBvalue = courseDao.getCourseLogs();
        assertEquals(1, DBvalue.size());

        assertEquals(testValue.getDescription(), DBvalue.get(0).getDescription());
        assertEquals(testValue.getInstructor(), DBvalue.get(0).getInstructor());
        assertEquals(testValue.getTitle(), DBvalue.get(0).getTitle());
        assertEquals(testValue.getStartDate(), DBvalue.get(0).getStartDate());
        assertEquals(testValue.getEndDate(), DBvalue.get(0).getEndDate());

    }


    /** Test case for update()
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


    /** Test case for delete()
     * 2/9/2020 Successfully passed test
     */
    @Test
    public void delete() {
        CourseLog testValue = new CourseLog("Instructor", "CST101",
                "Test Description", "1/1/1970", "1/1/2020");


        List<CourseLog> oldDBValue = courseDao.getCourseLogs();
        assertEquals(0, oldDBValue.size());
        courseDao.insert(testValue);

        List<CourseLog> newDBValue = courseDao.getCourseLogs();
        assertNotEquals(oldDBValue.size(), newDBValue.size());
    }

    @Test
    public void getCourseWithId() {
        CourseLog testValue = new CourseLog("Instructor", "CST101",
                "Test Description", "1/1/1970", "1/1/2020");

        courseDao.insert(testValue);
        List<CourseLog> DBValue = courseDao.getCourseLogs();
        CourseLog course = DBValue.get(0);
        int somethingID = course.getCourseID();
        CourseLog newthing = courseDao.getCourseWithId(somethingID);
        assertEquals(newthing.getInstructor(), testValue.getInstructor());
    }
}
