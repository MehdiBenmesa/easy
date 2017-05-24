package dz.easy.androidclient.Constants;

import dz.easy.androidclient.ServerAuthCodeActivity;

public interface Constants {

    public static final String SERVER_URL = "http://10.0.2.2:3000";
    //********** USER ***********//
    public static final String APP_LOGIN  = Constants.SERVER_URL+"/users/Auth-android";
    public static final String GCM_TOKEN  = "/users/gmcToken";

    //********** SCOLARITE ***********//

    public static final String GET_ALL_GROUPS  = SERVER_URL + "/scolarite/groupes";
    public static final String GET_MODULES_BY_TEACHER = SERVER_URL+"/scolarite/teacher/modules";
    public static final String GET_MODULES_BY_STUDENT = SERVER_URL+"/scolarite/modules-by-student";
    public static final String GET_TEACHERS = SERVER_URL+"/scolarite/teachers";
    public static final String GET_GROUPE_BY_MODULE = SERVER_URL + "/scolarite/groupes";
    public static final String GET_GROUPS_BY_TEACHER_MODULE = SERVER_URL+"/scolarite/groupes";
    public static final String GET_GROUPS_BY_TEACHER = SERVER_URL+"/scolarite/teacher/groupes";
    public static final String GET_MODULES_BY_SPEC = SERVER_URL + "/scolarite/modules-by-spec";

    //********** NOTES ***********//

    public static final String GET_NOTE_BY_MODULES  = SERVER_URL + "/notes/note-by-modules";
    public static final String POST_NOTE = SERVER_URL+"/notes/note";
    public static final String GET_NOTE_BY_STUDENT  = SERVER_URL + "/notes/note-by-student";
    public static final String POST_NOTES  = SERVER_URL + "/notes/notes";

    //********** ABSENCES ***********//

    public static final String GET_ABSENCE_BY_STUDENT = SERVER_URL + "/absences/student";
    public static final String GET_ABSENCE_BY_MODUL = SERVER_URL + "/absences/student";
    public static final String GET_ABSENCE_BY_SEANCE = SERVER_URL + "/absences/seance";
    public static final String POST_UPDATE_ABSENCES = SERVER_URL + "/absences/update";
    public static final String POST_ABSENCES = SERVER_URL + "/absences/";


    //********** RDV ***********//

    public static final String GET_RDV_BY_STUDENT = SERVER_URL + "/rdv/student";
    public static final String GET_RDV_BY_TEACHER = SERVER_URL + "/rdv/teacher";
    public static final String GET_RDV_ACCEPTED_STUDENT = SERVER_URL + "/rdv/accepted/student";
    public static final String GET_RDV_ACCEPTED_TEACHER = SERVER_URL + "/rdv/accepted/teacher";


    //********** EMPLOIE ***********//

    public static final String GET_TIME_TABLE_STUDENT = SERVER_URL+"/emploi";
    public static final String GET_TIME_TABLE_SALLE = SERVER_URL+"/emploi";
    public static final String GET_TIME_TABLE_TEACHER = SERVER_URL+"/emploi/teacher";
    public static final String GET_SEANCE_BY_TEACHER_DATE = SERVER_URL + "/emploi/teacher";



}
