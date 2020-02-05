package com.example.cst438project1.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438project1.DB.CourseDatabase;

import java.util.Objects;

/* I'll be honest, this file might be temporary while I
* learn Room and how it works
* */

@Entity(tableName = CourseDatabase.COURSELOG_TABLE)
public class CourseLog {

    @PrimaryKey(autoGenerate = true)
    private int mCourseID;

    private String mInstructor;
    private String mTitle;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;

    // Constructor
    public CourseLog(String instructor, String title, String description, String startDate,
                     String endDate)
    {
        /** JavaDoc for CourseLog.java
         * @param mInstructor is for the name of the course's instructor
         * @param mTitle is for the course's title
         * @param mDescription is for the course's description
         * @param mStartDate is for the course start date
         * @param mEndDate is for the course end date
         * @param mCourseID is for the course's ID, which is autogeneratem
         */
        mInstructor = instructor;
        mTitle = title;
        mDescription = description;
        mStartDate = startDate;
        mEndDate = endDate;
    }

    /** JavaDoc for Getters and Setters
     * ALL The getters and setters for variables used by database
     * Getter for CourseID added
     */
    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String instructor) {
        mInstructor = instructor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public int getCourseID() {
        return mCourseID;
    }

    public void setCourseID(int courseID) {
        mCourseID = courseID;
    }

    /** JavaDoc for equals, hashcode, and toString
     * I'm not sure if we'll need these, but I generated them just in case
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseLog courseLog = (CourseLog) o;
        return mCourseID == courseLog.mCourseID &&
                Objects.equals(mInstructor, courseLog.mInstructor) &&
                Objects.equals(mTitle, courseLog.mTitle) &&
                Objects.equals(mDescription, courseLog.mDescription) &&
                Objects.equals(mStartDate, courseLog.mStartDate) &&
                Objects.equals(mEndDate, courseLog.mEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCourseID, mInstructor, mTitle, mDescription, mStartDate, mEndDate);
    }

    @Override
    public String toString() {
        return "CourseLog{" +
                "mCourseID=" + mCourseID +
                ", mInstructor='" + mInstructor + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mStartDate='" + mStartDate + '\'' +
                ", mEndDate='" + mEndDate + '\'' +
                '}';
    }
}
