# AI Photo Studio

An Android-first photo enhancement app built with Kotlin and Jetpack Compose.

## Current experience

- Pick a JPG or PNG photo with the system photo picker.
- Preview the selected photo in the studio.
- Choose Professional, Social Media, or ID Photo enhancement.
- Run the enhancement flow with the `Enhance with AI` action.

The current repository implementation returns the original image from a fake enhancement service. This keeps the product flow testable while the network contract is being designed.

## Project structure

```text
app/src/main/java/com/aiphotostudio/app/
	data/       EnhancementRepository and its replaceable fake implementation
	domain/     EnhancementMode product model
	ui/         Compose screen and ViewModel state
	ui/theme/   Material 3 color theme
```

`EnhancementRepository` is the integration point for a real image enhancement API. A production implementation can be injected into `PhotoStudioViewModel` without changing the screen. Google Play Billing can follow the same boundary with a billing repository for subscription state and entitlement checks.

## Build locally

Open the project in Android Studio Ladybug or newer with:

- JDK 21
- Android SDK Platform 35
- Android SDK Build-Tools 35.x
- Android Gradle Plugin 8.7.3 (resolved by Gradle)

Then run:

```bash
gradle :app:assembleDebug
```

The current dev container does not include an Android SDK, so the build command cannot complete there until `ANDROID_HOME` or `local.properties` points to an installed SDK.

## Publication follow-up

Before Play publication, add a release signing configuration, privacy policy and data-safety declarations, network/API error states, image size and format validation, telemetry consent, and Google Play Billing product configuration.