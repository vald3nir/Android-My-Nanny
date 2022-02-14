# App My Nanny

A mobile app of an electronic babysitter for the IoT Discipline.

Master in Computer Science - UFC

## Overview

![144066611-343f705d-c392-4fe8-98a0-f574b05f7271](https://user-images.githubusercontent.com/23506996/152996890-75fba76d-2490-403e-b2ce-259852c3d194.png)

## Print Screens

| Splash Screen |
| ------------- |
| <img src="https://user-images.githubusercontent.com/23506996/153272303-e4996ccc-761b-4ccc-b859-020acabd86ca.gif" width="255">

| Login Screen | Register Screen |
| ------------- | ------------- |
| <img src="https://user-images.githubusercontent.com/23506996/153273061-a6d2c59d-e9f5-4980-aeaf-9c3b83a27f60.png" width="255"> | <img src="https://user-images.githubusercontent.com/23506996/153850001-c8086cce-6ff2-4e99-bdd8-1e5ce00e5c6e.png" width="255"> |

| Home Screen | Config Screen |
| ------------- | ------------- |
| <img src="https://user-images.githubusercontent.com/23506996/153853311-f389cc54-2b25-48fd-b38d-6fa79a3f5f82.gif" height="450" width="255"> | <img src="https://user-images.githubusercontent.com/23506996/153850563-5f6de8ec-0d9f-45a2-bab2-d883174364cc.png" width="255"> |

## Video server configuration on Raspberry Pi [Linux]

### Install Motion

    sudo apt-get install motion
  
### Config Motion

    sudo nano /etc/motion/motion.con

- Make sure 'daemon' is ON.
- Set 'framerate' anywhere in between 1000 to 1500.
- Keep 'Stream_port' to 8081.
- 'Stream_quality' should be 100.
- Change 'Stream_localhost' to OFF.
- Change 'webcontrol_localhost' to OFF.
- Set 'quality' to 100.
- Set 'width' & 'height' to 640 & 480.
- Set 'post_capture' to 5.
- Press ctrl + x to exit.

### Setup boot

    sudo nano /etc/default/motion

- Set ' start_motion_daemon ' to yes

### Run Motion

    sudo service motion restart
    sudo motion

### To show

    http://192.168.0.40:8081/

 
