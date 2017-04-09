/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cloudExplorer;

import static cloudExplorer.NewJFrame.jTextArea1;

public class MakeBucketThread implements Runnable {

    NewJFrame mainFrame;
    public static String response = null;
    public static String access_key = null;
    public static String secret_key = null;
    public static String endpoint = null;
    public static String region = null;
    public String bucket = null;

    public MakeBucketThread(String Aaccess_key, String Asecret_key, String Abucket, String Aendpoint, String Aregion, NewJFrame AmainFrame) {
        access_key = Aaccess_key;
        secret_key = Asecret_key;
        bucket = Abucket;
        endpoint = Aendpoint;
        mainFrame = AmainFrame;
        region = Aregion;

    }

    public void run() {
        try {
            BucketClass makebucket = new BucketClass();
            mainFrame.jTextArea1.append("\n" + makebucket.makeBucket(access_key, secret_key, bucket, endpoint, region));
            mainFrame.reloadBuckets();
        } catch (Exception makebucket) {
            jTextArea1.append("\n" + makebucket.getMessage());
        }
    }

    void startc(String Aaccess_key, String Asecret_key, String Abucket, String Aendpoint, String Aregion, NewJFrame AmainFrame) {
        (new Thread(new MakeBucketThread(Aaccess_key, Asecret_key, Abucket, Aendpoint, Aregion, AmainFrame))).start();
    }
}
