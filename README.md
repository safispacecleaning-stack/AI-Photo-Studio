# AI Photo Studio

An Android-first photo enhancement app built with Kotlin and Jetpack Compose.

## Current experience

- Pick a JPG or PNG photo with the system photo picker.
- Preview the selected photo in the studio.
- Choose Professional, Social Media, or ID Photo enhancement.
- Run the enhancement flow with the `Enhance with AI` action.

Enhancement currently runs on-device: the selected image is decoded, adjusted for the chosen mode, and written to a new JPEG in the app cache. The original image is never overwritten. The repository interface remains the integration point for a future hosted AI service.

## Project structure

```text
app/src/main/java/com/aiphotostudio/app/
	data/       EnhancementRepository and the local image processor
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
./gradlew :app:assembleDebug
```

The current dev container does not include an Android SDK, so the build command cannot complete there until `ANDROID_HOME` or `local.properties` points to an installed SDK.

## Play publication

The release build uses R8 shrinking and reads signing credentials from Gradle properties, never from source control. See [docs/PLAY_STORE_CHECKLIST.md](docs/PLAY_STORE_CHECKLIST.md) for the remaining publisher-owned steps and [docs/PRIVACY_POLICY.md](docs/PRIVACY_POLICY.md) for the policy that must be hosted at a public HTTPS URL.

The current app is offline and does not require a network permission. It does not include accounts, analytics, ads, or billing.