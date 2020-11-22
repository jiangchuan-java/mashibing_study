package java_normal.design_model.decorator;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/21 12:32
 */
public class DecoratorStudy {

    public static class Nokia implements Phone {
        @Override
        public void call() {
            System.out.println("诺基亚打电话");
        }
    }
    public static class BellPhoneDecorator implements Phone {
        private Phone phone;
        public BellPhoneDecorator(Phone phone) {
            this.phone = phone;
        }
        @Override
        public void call() {
            System.out.println("彩铃响起来");
            phone.call();
        }
    }
    public static class VoicePhoneDecorator implements Phone {
        private Phone phone;
        public VoicePhoneDecorator(Phone phone) {
            this.phone = phone;
        }
        @Override
        public void call() {
            System.out.println("智能语音助手，自动拨打电话");
            phone.call();
        }
    }
    public static void main(String[] args) {
        Phone nokia = new Nokia();
        BellPhoneDecorator bellPhone = new BellPhoneDecorator(nokia);
        VoicePhoneDecorator voicePhone = new VoicePhoneDecorator(bellPhone);
        voicePhone.call();
    }
}
