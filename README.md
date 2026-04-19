<h1 align="center">Quartz</h1>
<p align="center"><i>Lightweight Java library for handling player data in Minecraft plugins.</i></p>
<br>

## Description
Quartz is a lightweight java library for handling player data in Minecraft plugins. In a few lines of code, you can store and retrieve player data based off their UUID, with YAML-based storing under the hood. This removes the boiler plate code you have to write everytime you need to add data storing in your plugin.

## Installation guide (IntelliJ IDEA)
This guide assumes you have already installed the IntelliJ IDE and installed the Minecraft extension.
1. Download the latest release of <a href="https://github.com/Nebulations/quartz/releases/tag/Release">Quartz</a>. Make sure to store it at a proper location, as the file cannot be deleted in the future (unless you no longer need it)
1. Create a new paper plugin project.
1. In IntelliJ, click on the hamburger menu at the top left, File -> Project Structure. This should have opened a new menu called "Project Structure".
1. Under "Project Settings", click on "Libraries".
1. Add a new library using the plus icon above the text in the middle.
1. Click on "Java".
1. Locate the library in the file explorer that appeared on screen.
1. Click "OK", then "Apply".
1. Now you should be able to use Quartz from within your plugin.