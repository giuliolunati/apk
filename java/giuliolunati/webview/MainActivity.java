package giuliolunati.webview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebResourceRequest;
import androidx.webkit.WebViewAssetLoader;
import androidx.annotation.RequiresApi;

public class MainActivity extends Activity
{
  private static class LocalContentWebViewClient extends WebViewClient {

    private final WebViewAssetLoader mAssetLoader;

    LocalContentWebViewClient(WebViewAssetLoader assetLoader) {
      mAssetLoader = assetLoader;
    }

    @Override
    @RequiresApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
      return mAssetLoader.shouldInterceptRequest(request.getUrl());
    }

    @Override
    @SuppressWarnings("deprecation") // to support API < 21
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
      return mAssetLoader.shouldInterceptRequest(Uri.parse(url));
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
      .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
      .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(this))
      .build();
    WebView browser = (WebView) findViewById(R.id.webview);
    browser.setWebViewClient(new LocalContentWebViewClient(assetLoader));
    browser.loadUrl("https://appassets.androidplatform.net/assets/index.html");
  }
  public void quit(View v) { System.exit(0); } 
}


