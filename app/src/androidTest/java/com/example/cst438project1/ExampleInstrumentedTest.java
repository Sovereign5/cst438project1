package com.example.cst438project1;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.example.cst438project1.model.Assignment;
import com.example.cst438project1.model.AssignmentDAO;
import com.example.cst438project1.model.Grade;
import com.example.cst438project1.model.GradeDAO;
import com.example.cst438project1.model.AppDatabase;

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
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.cst438project1", appContext.getPackageName());
    }

    private AssignmentDAO assignmentDAO;
    private GradeDAO gradeDAO;
    private AppDatabase db;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        assignmentDAO = db.getAssignmentDAO();
        gradeDAO = db.getGradeDAO();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() {
        /////////////////////////
        // @Insert for Assignment
        /////////////////////////
        Assignment assignmentTestValue = new Assignment("Homework",
                100, 84, "02/09/2020",
                "02/20/2020", 1, 2);
        assignmentDAO.addAssignment(assignmentTestValue);
        List<Assignment> assignmentDBValue = assignmentDAO.getAllAssignments();

        assertEquals(1, assignmentDBValue.size());

        ////////////////////
        // @Insert for Grade
        ////////////////////
        Grade gradeTestValue = new Grade('A', 1 ,
                    1, 1, "02/07/2020");
        gradeDAO.addGrade(gradeTestValue);
        List<Grade> gradeDBValue = gradeDAO.getAllGrades();

        assertEquals(1, gradeDBValue.size());


    }

    // Passed all tests
    @Test
    public void update() {
        /////////////////////////////
        // @Update for Assignment DAO
        /////////////////////////////
        Assignment assignmentTestValue = new Assignment("Homework",
                100, 84, "02/09/2020",
                "02/20/2020", 1, 2);
        assignmentDAO.addAssignment(assignmentTestValue);
        List<Assignment> assignmentDBValue = assignmentDAO.getAllAssignments();

        // Updating values
        assignmentTestValue = assignmentDBValue.get(0);
        assignmentTestValue.setAssignedDate("03/20/2020");
        assignmentTestValue.setDetails("Quiz");
        assignmentTestValue.setDueDate("03/25/2020");
        assignmentTestValue.setEarnedScore(90);
        assignmentTestValue.setMaxScore(99);

        assignmentDAO.updateAssignment(assignmentTestValue);

        List<Assignment> assignmentDBValueUpdated = assignmentDAO.getAllAssignments();
        Assignment assignmentUpdated = assignmentDBValueUpdated.get(0);

        assertEquals(assignmentTestValue.getAssignedDate(), assignmentUpdated.getAssignedDate());
        assertEquals(assignmentTestValue.getDetails(), assignmentUpdated.getDetails());
        assertEquals(assignmentTestValue.getDueDate(), assignmentUpdated.getDueDate());
        assertEquals(assignmentTestValue.getEarnedScore(), assignmentUpdated.getEarnedScore(), 0);
        assertEquals(assignmentTestValue.getMaxScore(), assignmentUpdated.getMaxScore(), 0);

        ////////////////////////
        // @Update for Grade DAO
        ////////////////////////
        Grade gradeTestValue = new Grade('A', 1 ,
                1, 1, "02/07/2020");
        gradeDAO.addGrade(gradeTestValue);
        List<Grade> gradeDBValue = gradeDAO.getAllGrades();

        // Updating values
        gradeTestValue = gradeDBValue.get(0);
        gradeTestValue.setScore('B');
        gradeTestValue.setAssignmentID(2);
        gradeTestValue.setStudentID(2);
        gradeTestValue.setDateEarned("03/08/2020");

        gradeDAO.updateGrade(gradeTestValue);
        List<Grade> gradeDBValueUpdated = gradeDAO.getAllGrades();
        Grade gradeUpdated = gradeDBValueUpdated.get(0);

        assertEquals(gradeTestValue.getScore(), gradeUpdated.getScore());
        assertEquals(gradeTestValue.getAssignmentID(), gradeUpdated.getAssignmentID());
        assertEquals(gradeTestValue.getStudentID(), gradeUpdated.getStudentID());
        assertEquals(gradeTestValue.getDateEarned(), gradeUpdated.getDateEarned());
    }

    @Test
    public void delete() {
        /////////////////////////////
        // @Delete for Assignment DAO
        /////////////////////////////
        Assignment assignmentTestValue = new Assignment("Homework",
        100, 84, "02/09/2020",
        "02/20/2020", 1, 2);
        assignmentDAO.addAssignment(assignmentTestValue);
        List<Assignment> assignmentDBValue = assignmentDAO.getAllAssignments();

        assertEquals(1, assignmentDBValue.size());

        assignmentDAO.deleteAssignment(assignmentTestValue);

        List<Assignment> deletedAssignmentDAO = assignmentDAO.getAllAssignments();
        assertEquals(1, deletedAssignmentDAO.size());

        ////////////////////////
        // @Delete for Grade DAO
        ////////////////////////
        Grade gradeTestValue = new Grade('A', 1 ,
                1, 1, "02/07/2020");
        gradeDAO.addGrade(gradeTestValue);
        List<Grade> gradeDBValue = gradeDAO.getAllGrades();

        assertEquals(1, gradeDBValue.size());

        gradeDAO.deleteGrade(gradeTestValue);

        List<Grade> deletedGradeDAO = gradeDAO.getAllGrades();
        assertEquals(1, deletedGradeDAO.size());
    }



}
