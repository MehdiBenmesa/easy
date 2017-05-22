package dz.easy.androidclient.Constants;

import dz.easy.androidclient.ServerAuthCodeActivity;

public interface Constants {

    public static final String SERVER_URL = "http://10.0.2.2:3000";
    public static final String GET_ALL_GROUPS  = SERVER_URL + "/scolarite/groupes";
    public static final String APP_LOGIN  = Constants.SERVER_URL+"/users/Auth-android";
    public static final String GET_MODULES_BY_TEACHER = SERVER_URL+"/scolarite/modules-by-teacher";
    public static final String GET_MODULES_BY_STUDENT = SERVER_URL+"/scolarite/modules-by-student";
    public static final String GET_TEACHERS = SERVER_URL+"/scolarite/teachers";
    public static final String GET_NOTE_BY_STUDENT  = SERVER_URL + "/notes/note-by-student";
    public static final String GET_MODULES_BY_SPEC = SERVER_URL + "/scolarite/modules-by-spec";
    public static final String GET_ABSENCE_BY_STUDENT = SERVER_URL + "/absences/student";
    public static final String GET_GROUPE_BY_MODULE = SERVER_URL + "/scolarite/groupes";
    public static final String GET_ABSENCE_BY_MODUL = SERVER_URL + "/absences/student";
    public static final String GET_TIME_TABLE = SERVER_URL+"/emploi";
    public static final String GET_NOTE_BY_MODULES  = SERVER_URL + "/notes/note-by-modules";
    public static final String GET_GROUPS_BY_TEACHER_MODULE = SERVER_URL+"/scolarite/groupes";



}
