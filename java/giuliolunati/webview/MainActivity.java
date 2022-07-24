package giuliolunati.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }
  public void quit(View v) { System.exit(0); } 
}
