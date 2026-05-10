public class OperaionCalss implements AirthemeticOperation {
    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public int div(int a, int b) {
        return a / b;
    }
}