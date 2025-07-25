[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![JitPack][jitpack-shield]][jitpack-url]

<a></a>
![Moon_UP](https://user-images.githubusercontent.com/72404424/188732547-f1d9f84e-f4fa-4d76-809e-7bab7e40d41a.png)

<h1 align="center">MoonAPI</h1>
<p align="center">A Framework plugin with different features to use in your plugin!</p>


<br />
<p align="center"><b>⚠️ You need to install MoonAPI.java from releases or shade it into your plugin in order for your plugin to work!</b></p>
<br />

<p align="center"><a href="https://github.com/MoonDevelopment-Gio/MoonAPI/issues">Report Bug</a> - <a href="https://github.com/MoonDevelopment-Gio/MoonAPI/issues">Request Feature</a> - <a href="https://discord.com/users/287196822521249792">Discord</a></p>

<br />

<h1 align="center">Spigot</h1>
<h2 align="center">Features</h2>

| Feature           | Implemented        |
|-------------------|--------------------|
| BlockExplosion    | :white_check_mark: |
| CommandManager    | :white_check_mark: |
| PermissionManager | :white_check_mark: |
| Cooldown          | :white_check_mark: |
| Database          | :white_check_mark: |
| Encryption        | :white_check_mark: |
| GUI               | :white_check_mark: |
| ItemBuilder       | :white_check_mark: |
| Kits              | :white_check_mark: |
| Modules           | :white_check_mark: |
| Scoreboard        | :white_check_mark: |
| Title             | :white_check_mark: |


<br />

<h2 align="center">Utils</h2>

| Utils          | Implemented        |
|----------------|--------------------|
| ChatUtil       | :white_check_mark: |
| ColorUtil      | :white_check_mark: |
| ConfigUtil     | :white_check_mark: |
| DatabaseUtil   | :white_check_mark: |
| EncoderUtil    | :white_check_mark: |
| LoggerUtil     | :white_check_mark: |
| MathUtil       | :white_check_mark: |
| TimeUtil       | :white_check_mark: |
| VersionUtil    | :white_check_mark: |
| YamlConfigUtil | :white_check_mark: |


<br />

<h1 align="center">Bungeecord & Velocity</h1>
<h2 align="center">Features</h2>

| Feature           | Implemented        |
|-------------------|--------------------|
| Cooldown          | :white_check_mark: |
| Database          | :white_check_mark: |
| Encryption        | :white_check_mark: |
| Modules           | :white_check_mark: |

<br />

<h2 align="center">Utils</h2>

| Utils          | Implemented        |
|----------------|--------------------|
| ChatUtil       | :white_check_mark: |
| ColorUtil      | :white_check_mark: |
| DatabaseUtil   | :white_check_mark: |
| EncoderUtil    | :white_check_mark: |
| LoggerUtil     | :white_check_mark: |
| MathUtil       | :white_check_mark: |
| TimeUtil       | :white_check_mark: |


<br />

<h1 align="center">Maven</h1>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<!-- Bungee -->
<dependency>
    <groupId>com.github.U911-Gio.MoonAPI</groupId>
    <artifactId>MoonAPI-BUNGEE</artifactId>
    <version>v2.1.1</version>
</dependency>

<!-- Velocity -->
<dependency>
    <groupId>com.github.U911-Gio.MoonAPI</groupId>
    <artifactId>MoonAPI-VELOCITY</artifactId>
    <version>v2.1.1</version>
</dependency>



<!-- Spigot -->
<dependency>
    <groupId>com.github.U911-Gio.MoonAPI</groupId>
    <artifactId>MoonAPI-SPIGOT</artifactId>
    <version>v2.1.1</version>
</dependency>
```

<br/>

<h1 align="center">Gradle</h1>

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

// Bungee
dependencies {
    implementation 'com.github.U911-Gio.MoonAPI:MoonAPI-BUNGEE:v2.1.1'
}

// Velocity
dependencies {
    implementation 'com.github.U911-Gio.MoonAPI:MoonAPI-VELOCITY:v2.1.1'
}

// Spigot
dependencies {
    implementation 'com.github.U911-Gio.MoonAPI:MoonAPI-SPIGOT:v2.1.1'
}
```

<br />

<h1 align="center">Wiki</h1>
<p align="center">Check it out <a href="https://github.com/MoonDevelopment-Gio/MoonAPI/wiki">here</a></p>

<br />

<h1 align="center">License</h1>
<p align="center">Distributed under the GNU General Public License v2.0 License. See <b>LICENSE</b> for more information.</p>

![Moon_DOWN](https://user-images.githubusercontent.com/72404424/188733603-e19c2bb0-312d-4cb1-b71d-ad1d68c459c6.png)


<!-- Markdown Links & Images -->
[contributors-shield]: https://img.shields.io/github/contributors/MoonDevelopment-Gio/MoonAPI.svg?style=for-the-badge
[contributors-url]: https://github.com/MoonDevelopment-Gio/MoonAPI/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/MoonDevelopment-Gio/MoonAPI.svg?style=for-the-badge
[forks-url]: https://github.com/MoonDevelopment-Gio/MoonAPI/network/members
[stars-shield]: https://img.shields.io/github/stars/MoonDevelopment-Gio/MoonAPI.svg?style=for-the-badge
[stars-url]: https://github.com/MoonDevelopment-Gio/MoonAPI/stargazers
[issues-shield]: https://img.shields.io/github/issues/MoonDevelopment-Gio/MoonAPI.svg?style=for-the-badge
[issues-url]: https://github.com/MoonDevelopment-Gio/MoonAPI/issues
[license-shield]: https://img.shields.io/github/license/MoonDevelopment-Gio/MoonAPI.svg?style=for-the-badge
[license-url]: https://github.com/MoonDevelopment-Gio/MoonAPI/blob/master/LICENSE
[jitpack-shield]: https://img.shields.io/jitpack/version/be.moondevelopment/MoonAPI?color=green&style=for-the-badge
[jitpack-url]: https://jitpack.io/#be.moondevelopment/MoonAPI
