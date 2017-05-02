package dz.easy.androidclient.Constants;

public interface Constants {

    public static final String SERVER_URL = "http://10.0.2.2:3000";
    public static final String GET_ALL_GROUPS  = SERVER_URL + "/scolarite/groupes";
    public static final String APP_LOGIN  = Constants.SERVER_URL+"/Auth-android";
    public static final String GET_MODULES_BY_TEACHER = Constants.SERVER_URL+"/scolarite/modules-by-teacher";
    public static final String GET_MODULES_BY_STUDENT = Constants.SERVER_URL+"/scolarite/modules-by-student";
    public static final String GET_TEACHERS = Constants.SERVER_URL+"/scolarite/teachers";
}
