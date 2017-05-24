package dz.easy.androidclient.Model;


public class RequestBody {

    private String deviceName;
    private String deviceId;
    private String registrationId;
    private String email ;

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setEmail(String email) { this.email = email; }
}
