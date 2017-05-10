package dz.easy.androidclient.Constants;

public interface Constants {

    public static final String GET_TIME_TABLE = Constants.SERVER_URL+"/emploi";
    public static final String SERVER_URL = "http://192.168.1.2:3000";
    public static final String GET_ALL_GROUPS  = SERVER_URL + "/scolarite/groupes";
    public static final String APP_LOGIN  = Constants.SERVER_URL+"/users/Auth-android";
    public static final String GET_MODULES_BY_TEACHER = Constants.SERVER_URL+"/scolarite/modules-by-teacher";
    public static final String GET_MODULES_BY_STUDENT = Constants.SERVER_URL+"/scolarite/modules-by-student";
    public static final String GET_TEACHERS = Constants.SERVER_URL+"/scolarite/teachers";
    public static final String GET_NOTE_BY_STUDENT  = SERVER_URL + "/notes/note-by-student";
    public static final String GET_MODULES_BY_SPEC = SERVER_URL + "/scolarite/modules-by-spec";

}
