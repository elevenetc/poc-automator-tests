package poc.automatortests;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String PACKAGE = "poc.sharedstatetest";

    @Test
    public void sampleTest() throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        putValue(InstrumentationRegistry.getTargetContext());

        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), 5000);


        UiObject btnA = device.findObject(new UiSelector().resourceId("poc.sharedstatetest:id/btn_a"));
        UiObject text = device.findObject(new UiSelector().resourceId("poc.sharedstatetest:id/text_status"));

        btnA.click();

        assertEquals("a", text.getText());

        device.pressHome();
    }

    private void putValue(Context targetContext) {
        Intent i = new Intent("ExchangeAction");
        i.putExtra("value", "newValue");
        targetContext.sendBroadcast(i);
    }
}