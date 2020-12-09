package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportValidator {
    public static List<String> validate(Report r){
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(r.getTitle());
        if(!title_error.equals("")){
            errors.add(title_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        String attendance_at_error = _validateAttendance_at(r.getAttendance_at());
        if(!attendance_at_error.equals("")){
            errors.add(attendance_at_error);
        }

        String leave_work_at_error = _validateLeave_work_at(r.getLeave_work_at());
        if(!leave_work_at_error.equals("")){
            errors.add(leave_work_at_error);
        }

        return errors;
    }

    private static String _validateTitle(String title){
        if(title == null || title.equals("")){
            return "タイトルを入力してください。";
        }
        return "";
    }

    private static String _validateContent(String content){
        if(content == null || content.equals("")){
            return "内容を入力してください";
        }
        return "";
    }

    private static String _validateAttendance_at(String attendance_at){
        if(attendance_at == null || attendance_at.equals("")){
            return "出勤時間を入力してください。";
        }
        return "";
    }

    private static String _validateLeave_work_at(String leave_work_at){
        if(leave_work_at == null || leave_work_at.equals("")){
            return "退勤時間を入力してください。";
        }
        return "";
    }

}
