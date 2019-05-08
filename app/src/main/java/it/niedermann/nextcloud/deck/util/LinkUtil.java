package it.niedermann.nextcloud.deck.util;

import android.content.res.Resources;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public final class LinkUtil {
    private LinkUtil() {
    }

    /**
     * Creates a {@link Spanned} from a HTML string on all SDK versions.
     *
     * @param source Source string with HTML markup
     * @return Spannable for using in a {@link TextView}
     * @see Html#fromHtml(String)
     * @see Html#fromHtml(String, int)
     */
    private static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    /**
     * Fills a {@link TextView} with HTML content and activates links in that {@link TextView}.
     *
     * @param view       The {@link TextView} which should be filled.
     * @param stringId   The string resource containing HTML tags (escaped by <code>&lt;</code>)
     * @param formatArgs Arguments for the string resource.
     */
    public static void setHtml(TextView view, int stringId, Object... formatArgs) {
        view.setText(LinkUtil.fromHtml(view.getResources().getString(stringId, formatArgs)));
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * Fills a {@link TextView} with HTML content and activates links in that {@link TextView}.
     *
     * @param view      The {@link TextView} which should be filled.
     * @param stringIds The string resource containing HTML tags (escaped by <code>&lt;</code>)
     */
    public static void setHtmlFromStringResources(TextView view, String... stringIds) {

        StringBuilder sb = new StringBuilder();
        for (String arg : stringIds) {
            sb.append(arg);
        }

        view.setText(LinkUtil.fromHtml(sb.toString()));
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static String concatenateResources(Resources resources, int... stringIds) {
        StringBuilder sb = new StringBuilder();
        for (int arg : stringIds) {
            sb.append(resources.getString(arg));
        }
        return sb.toString();
    }
}
