/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.sonis.autotyper;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import javax.swing.KeyStroke;

/**
 *
 * @author Leach
 */
public class HotKeyToggler {

    public boolean isOn = false;

    public boolean isOn() {
        return this.isOn;
    }

    public HotKeyToggler(KeyStroke keystroke) {
        if (AutoTyperApplication.provider == null) {
            return;
        }
        AutoTyperApplication.provider.register(keystroke, new HotKeyListener() {
            public void onHotKey(HotKey key) {
                HotKeyToggler.this.isOn = (!HotKeyToggler.this.isOn);
            }
        });
    }
}
