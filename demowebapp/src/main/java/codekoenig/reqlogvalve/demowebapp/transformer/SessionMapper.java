package codekoenig.reqlogvalve.demowebapp.transformer;

public class SessionMapper {

    private static String jSessionId;
    private static String casaPrefix;
    private static String currentProcessId;

    public static String getjSessionId() {
        return jSessionId;
    }

    public static void setjSessionId(String jSessionId) {
        SessionMapper.jSessionId = jSessionId;
    }

    public static void setCasaPrefix(String casaPrefix) {
        SessionMapper.casaPrefix = casaPrefix;
    }

    public static String getCasaPrefix() {
        return casaPrefix;
    }

    public static void setCurrentProcessId(String currentProcessId) {
        SessionMapper.currentProcessId = currentProcessId;
    }

    public static String getCurrentProcessId() {
        return currentProcessId;
    }

    // region Properties
    /*
    private Map<String, String> jSessionMap;
    private Map<String, String> casaSessionMap;
    private Map<String, String> subSessionMap;

    public Map<String, String> getjSessionMap() {
        return jSessionMap;
    }

    public void setjSessionMap(Map<String, String> jSessionMap) {
        this.jSessionMap = jSessionMap;
    }

    public Map<String, String> getCasaSessionMap() {
        return casaSessionMap;
    }

    public void setCasaSessionMap(Map<String, String> casaSessionMap) {
        this.casaSessionMap = casaSessionMap;
    }

    public Map<String, String> getSubSessionMap() {
        return subSessionMap;
    }

    public void setSubSessionMap(Map<String, String> subSessionMap) {
        this.subSessionMap = subSessionMap;
    }
    */
    // endregion
}
