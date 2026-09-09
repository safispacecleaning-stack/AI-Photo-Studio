# Google Play release checklist

## Before upload

- Replace `REPLACE_WITH_SUPPORT_EMAIL` in the privacy policy and publish it at a public HTTPS URL.
- Create a release keystore and keep it outside the repository.
- Add the release signing properties locally or through CI secrets:
  `RELEASE_STORE_FILE`, `RELEASE_STORE_PASSWORD`, `RELEASE_KEY_ALIAS`, and `RELEASE_KEY_PASSWORD`.
- Set a unique application ID if `com.aiphotostudio.app` is not owned by the publisher.
- Run `./gradlew :app:bundleRelease` on a machine with Android SDK Platform 35 installed.
- Test the signed bundle on Android 8 through Android 15, including photo picker, enhancement, rotation, and low-storage behavior.

## Play Console

- Upload the signed Android App Bundle from `app/build/outputs/bundle/release/`.
- Complete the Data safety form: no data collection or sharing is declared for the current offline implementation.
- Add the hosted privacy-policy URL.
- Add an app icon, screenshots, short description, full description, category, and support email.
- Complete content rating, target-audience, ads, and app-access declarations.
- Start an internal test before production rollout.

The release build is configured to use R8 shrinking and external signing properties.
Never commit a keystore or passwords.