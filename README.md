# Tiler Buddy

## About The Project

<p>It is an application built for Tilers, helping them to easily visualize and calculate the positioning and size of the tiles
they are about to put on the floor or wall(s).</p>
<p>It is also a Learning Project to master the basics of Java.</p>

Built with Android Studio (Java)

## Getting Started:

- First you need to [download the apk.](https://1drv.ms/u/s!AhqBQd6aND_8kh1RZxEkEsOLzcKQ?e=k2T3Et)
- Now you have 2 options:
    - You can install it to your own device(s).
    - You can use an emulator.

  ### Emulator:

- First you need to install android sdk. [Download here](https://developer.android.com/studio)
- Next you need to set up
  the [Environment Variables.](https://developer.android.com/studio/command-line/variables)
- Open up Android Studio and go to <em>'Tools\Device Manager\Create Device'</em>
- After you created the virtual device you wish to use, you can close Android Studio.
- Open a command line prompt and start your emulator with the following command:
  <br><code>emulator -avd NAME_OF_YOUR_DEVICE</code>
- Next you'll need to install the app.
    - Open another command prompt and head to the location of your sdk installation folder in CMD.
      <br><em>(Default: C:\Users\USER\AppData\Local\Android\Sdk\platform-tools)</em>
    - Run the following command to install the app:
      <br><code>adb install "C:\LOCATION\OF\tiler_buddy.apk"</code>

## Usage

When opening the app, first you'll see the <em>Input page</em>, where you need to add the dimensions
of the

- area to be tiled,
- tiles you are about the use,
- the obstacles (if have any)

<p>TO_BE_ADDED: calculating extra 10% for amount of tiles (if there are many cuts to do)</p>
<p>The application calculates the amount of tiles you'll need to cover the surface, and also calculates the cuts you'll need to do,
and draws it to the screen, placing the tiles from left to right, from bottom to top.
</p>
<p>
You can drag any row of tiles using 1 finger, repositioning all tiles in the given row.
<br>You can also drag the whole wall of tiles, by using 2 or more fingers.
</p>

#### TO BE ADDED:

- Automatic 'sorting' for symmetric placement by placing one tile exactly at the middle
- Automatic 'sorting' for symmetric placement by placing tiles right next to middle.
- Automatic 'sorting' for 1/4 - 1/2 shift for each row
- Taking picture and selecting 'to be tiled area' on it, then resizing and placing tiles at 'to be tiled are'