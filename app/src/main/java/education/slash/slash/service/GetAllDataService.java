package education.slash.slash.service;

import education.slash.slash.model.DatabaseDate.DatabaseDateCls;
import education.slash.slash.model.Income.IncomeCls;
import education.slash.slash.model.PendingCharge.PendingChargeCls;
import education.slash.slash.model.Student.StudentCls;
import education.slash.slash.model.StudentPartial.StudentPartialCls;
import education.slash.slash.model.StudentPaymentHistory.PaymentHistoryCls;
import education.slash.slash.model.StudentProfile.StudentProfileCls;
import education.slash.slash.model.Subject.Subjectcls;
import education.slash.slash.model.Teacher.TeacherCls;
import education.slash.slash.model.TeacherProfile.TeacherProfileCls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Prashabt on 7/16/2018.
 */

public interface GetAllDataService {

    @GET("getCourse.php")
    Call<Subjectcls> getAllSubjectResult();

    @GET("getCourse.php?status=1")
    Call<Subjectcls> getActiveSubjectResult();

    @GET("getCourse.php?status=0")
    Call<Subjectcls> getInactiveSubjectResult();

    @GET("getTeacher.php")
    Call<TeacherCls> getAllTeacherResult();

    @GET("getTeacher.php?status=1")
    Call<TeacherCls> getActiveTeacherResult();

    @GET("getTeacher.php?status=0")
    Call<TeacherCls> getInactiveTeacherResult();

    @GET("getTeacherProfile.php")
    Call<TeacherProfileCls> getTeacherProfileResult(@Query("id") String id);

    @GET("getStudent.php")
    Call<StudentCls> getAllStudentResult();

    @GET("getStudent.php?status=1")
    Call<StudentCls> getActiveStudentResult();

    @GET("getStudent.php?status=0")
    Call<StudentCls> getInactiveStudentResult();

    @GET("getStudentProfile.php")
    Call<StudentProfileCls> getStudentProfileResult(@Query("id") String id);

    @GET("getPaymentHistory.php")
    Call<PaymentHistoryCls> getStudentPaymentHistory(@Query("id") String id);

    @GET("getStudentPartial.php")
    Call<StudentPartialCls> getStudetnPartial(@Query("teacherId") String teacherid,@Query("courseId") String courseid);

    @GET("getPendingCharge.php")
    Call<PendingChargeCls> getPendingCharge();

    @GET("getIncome.php")
    Call<IncomeCls> getIncome(@Query("type") String type,@Query("startDate") String startDate,@Query("endDate") String endDate);

    @GET("getDatabaseDate.php")
    Call<DatabaseDateCls> getDatabaseDate();

}
