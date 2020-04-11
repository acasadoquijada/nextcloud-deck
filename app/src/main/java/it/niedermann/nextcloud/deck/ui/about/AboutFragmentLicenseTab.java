package it.niedermann.nextcloud.deck.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import it.niedermann.nextcloud.deck.Application;
import it.niedermann.nextcloud.deck.R;
import it.niedermann.nextcloud.deck.databinding.FragmentAboutLicenseTabBinding;
import it.niedermann.nextcloud.deck.ui.branding.Branded;

import static it.niedermann.nextcloud.deck.util.SpannableUtil.setTextWithURL;

public class AboutFragmentLicenseTab extends Fragment implements Branded {

    private FragmentAboutLicenseTabBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutLicenseTabBinding.inflate(inflater, container, false);
        Application.registerBrandedComponent(requireContext(), this);
        setTextWithURL(binding.aboutIconsDisclaimerAppIcon, getResources(), R.string.about_icons_disclaimer_app_icon, R.string.about_app_icon_author_link_label, R.string.url_about_icon_author);
        setTextWithURL(binding.aboutIconsDisclaimerMdiIcons, getResources(), R.string.about_icons_disclaimer_mdi_icons, R.string.about_icons_disclaimer_mdi, R.string.url_about_icons_disclaimer_mdi);
        binding.aboutAppLicenseButton.setOnClickListener((v) -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_license)))));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        Application.registerBrandedComponent(requireContext(), this);
        super.onResume();
    }

    @Override
    public void onPause() {
        Application.deregisterBrandedComponent(this);
        super.onPause();
    }

    @Override
    public void applyBrand(int mainColor, int textColor) {
        binding.aboutAppLicenseButton.setBackgroundColor(mainColor);
        binding.aboutAppLicenseButton.setTextColor(textColor);
    }
}