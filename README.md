# Stechoq-Skill-Test
Submission for Bareksa's Android Developer Stechoq Skill Test


## Screenshot
<p float="left">
  <img src="https://github.com/furqoncreative/Stechoq-Skill-Test/blob/dev/assets/Screenshot_20210630-193026_Stechoq%20TODO.jpg" alt="splash" width="200"/>
  <img src="https://github.com/furqoncreative/Stechoq-Skill-Test/blob/dev/assets/Screenshot_20210630-204256_Stechoq%20TODO.jpg" alt="main" width="200"/>
</p>

## Structure

* `build.gradle` - root gradle config file
* `settings.gradle` - root gradle settings file
* `shared_dependencies.gradle` - shared gradle settings file
* `app` - main-feature file
* `app/build.gradle` - main gradle config file
* `app/src` - main project source directory
* `app/src/main` - main project flavour
* `app/src/main/AndroidManifest.xml` - manifest file
* `app/src/main/java` - kotlin source directory
* `app/src/main/res` - resources directory
* `core` - core-feature file
* `core/build.gradle` - core gradle config file
* `core/src` - core project source directory
* `core/src/main` - core project flavour
* `core/src/main/AndroidManifest.xml` - core manifest file
* `core/src/main/java` - core kotlin source directory
* `core/src/main/res` - core resources directory
## Building


#### Clean

	gradle clean

#### Debug

This compiles a debugging apk in `build/outputs/apk/` signed with a debug key,
ready to be installed for testing purposes.

	gradle assembleDebug

You can also install it on your attached device:

	gradle installDebug

#### Release

This compiles an unsigned release (non-debugging) apk in `build/outputs/apk/`.
It's not signed, you must sign it before it can be installed by any users.

	gradle assembleRelease

#### Test

Were you to add automated java tests, you could configure them in your
`build.gradle` file and run them within gradle as well.

	gradle test

#### Lint

This analyses the code and produces reports containing warnings about your
application in `build/outputs/lint/`.

	gradle lint
