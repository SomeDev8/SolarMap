package com.rorysoft.solarmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rorysoft.solarmap.database.DatabaseController;
import com.rorysoft.solarmap.fragment.AboutFragment;
import com.rorysoft.solarmap.fragment.ObjFragment;

public class Map extends AppCompatActivity {

    private ImageView sun, earth, jupiter, mars, mercury, neptune,
            pluto, saturn, uranus, venus, moon, infoButton, musicToggle, homeButton;
    DatabaseController databaseController = new DatabaseController(this);
    private Animation compileAnim;
    private boolean isDbCreated;
    private boolean isMusicOn;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        sun = (ImageView) findViewById(R.id.sun);
        mercury = (ImageView) findViewById(R.id.mercury);
        venus = (ImageView) findViewById(R.id.venus);
        earth = (ImageView) findViewById(R.id.earth);
        mars = (ImageView) findViewById(R.id.mars);
        jupiter = (ImageView) findViewById(R.id.jupiter);
        saturn = (ImageView) findViewById(R.id.saturn);
        uranus = (ImageView) findViewById(R.id.uranus);
        neptune = (ImageView) findViewById(R.id.neptune);
        pluto = (ImageView) findViewById(R.id.pluto);
        moon = (ImageView) findViewById(R.id.moon);
        infoButton = (ImageView) findViewById(R.id.infoButton);
        musicToggle = (ImageView) findViewById(R.id.musicToggle);
        homeButton = (ImageView) findViewById(R.id.homeButton);

        // all views are initialized and ready to be used

        initHomeButton();
        initMusicToggle();
        initInfoButton();
        initSun();
        initMercury();
        initVenus();
        initEarth();
        initMars();
        initJupiter();
        initSaturn();
        initUranus();
        initNeptune();
        initPluto();
        initMoon();

        // The database is only created, the shared preference will contain a false by default
        // When the database is created, a true will be stored to ensure it is never created again

        databaseController.open();
        sharedPreferences = getApplicationContext().getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        isDbCreated = sharedPreferences.getBoolean(Constants.IS_CREATED, false);
        if (!isDbCreated) {
            databaseController.createData();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.IS_CREATED, true);
            editor.apply();
        }
    }

    // When the task is in the background, we want to pause the audio so it doesn't play

    @Override
    protected void onPause() {
        super.onPause();
        if (isMusicOn) {
            mediaPlayer3.pause();
        }
    }

    // When the orientation of the app is changed, the current state of the music track is saved if it is playing

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (isMusicOn) {
            outState.putInt("position", mediaPlayer3.getCurrentPosition());
            mediaPlayer3.pause();
        }
        super.onSaveInstanceState(outState);
    }

    // If the music track was playing and the activity was destroyed, the progress of the track is restored

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (isMusicOn) {
            int pos = savedInstanceState.getInt("position");
            mediaPlayer3.seekTo(pos);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    // When the application starts, the build version will be checked. If it is greater than
    // API 16, immersive flags will be initiated, otherwise fullscreen will be enabled on
    // API 16 or less. Also, the music track, if playing, will be paused.

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {

            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (isMusicOn) {
            mediaPlayer3.start();
        }
    }

    // The above method seems to be sufficient for changing the focus, but some pesky apps
    // like CM master load a ad filled lockscreen that overrides flags initiated above
    // this method hides the status bar and starts immersive mode.

    public void onWindowFocusChanged(boolean hasFocus) {

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            View decorView = getWindow().getDecorView();
            super.onWindowFocusChanged(hasFocus);
            if (hasFocus) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

            }
        }
    }

    // Info button is set up for animated effect if it is pressed and the fragment is started. The info button cannot
    // be pressed if any fragment

    public void initInfoButton() {

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getSupportFragmentManager().findFragmentByTag("aboutFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("objFragment") == null) {
                    compileAnim = AnimationUtils.loadAnimation(Map.this, R.anim.blink);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mediaPlayer2 = MediaPlayer.create(Map.this, R.raw.beep_info);
                            mediaPlayer2.start();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            AboutFragment aboutFragment = new AboutFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContainer, aboutFragment, "aboutFragment")
                                    .addToBackStack("aboutFragment")
                                    .commit();

                            mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer2.reset();
                                    mediaPlayer2.release();
                                }
                            });
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    infoButton.startAnimation(compileAnim);
                }
            }
        });
    }

    // The home button is initiated, but it cannot be clicked if any fragment is open. When pressed it will start onPause
    // method  and return the user to the android home screen


    public void initHomeButton() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("aboutFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("objFragment") == null) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                }
            }
        });
    }

    // The music button is initialized for toggling music on and off. First, the shared preferences is accessed and
    // a check is performed to find whether the user had music on or off when the app was last open. Based on their
    // choice, an on or off resource will be set and the music will play or stop based on the users choice.
    // The button cannot be pressed if any fragments are open.

    public void initMusicToggle() {

        sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        isMusicOn = sharedPreferences.getBoolean(Constants.IS_MUSIC_ON, true);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.track);

        if (isMusicOn) {
            musicToggle.setImageResource(R.drawable.music_off);
            mediaPlayer3.start();
            mediaPlayer3.setLooping(true);
        } else {
            musicToggle.setImageResource(R.drawable.music_on_off);
        }

        musicToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("aboutFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("objFragment") == null) {
                    compileAnim = AnimationUtils.loadAnimation(Map.this, R.anim.blink);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            musicToggle.setImageDrawable(null);
                            if (isMusicOn) {
                                isMusicOn = false;
                                mediaPlayer3.stop();
                                mediaPlayer3.reset();
                                mediaPlayer3.release();
                                musicToggle.setImageResource(R.drawable.music_on_off);

                            } else {
                                mediaPlayer3 = MediaPlayer.create(Map.this, R.raw.track);
                                mediaPlayer3.start();
                                mediaPlayer3.setLooping(true);
                                isMusicOn = true;
                                musicToggle.setImageResource(R.drawable.music_off);
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Constants.IS_MUSIC_ON, isMusicOn);
                            editor.apply();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    musicToggle.startAnimation(compileAnim);
                }
            }
        });
    }


    // All views for the planets and Sun are initiated for onClick and animations. When the user clicks the imageviews
    // the animation will play and audible sound will start and a fragment will be created based on the database
    // row integer. All values from the database will be passed with the transaction. If any fragment is open
    // nothing on the map activity will be clickable.

    private void initSun() {

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {

                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }

                            Cursor cursor = databaseController.getData(1);
                            ObjFragment objFragment;

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.sun_detail;
                            String url = "<html><a href=\"http://www.nasa.gov/sun\">Visit NASA</a> for up-to-date info</html>";
                            objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    sun.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initMercury() {

        mercury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(2);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.mercury_detail;
                            String url = "<html><a href=\"http://www.nasa.gov/planetmercury\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    mercury.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initVenus() {

        venus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {

                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(3);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.venus_detail;
                            String url = "<html><a href=\"https://www.nasa.gov/venus\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    venus.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initEarth() {


        earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(4);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.earth_detail;
                            String url = "<html><a href=\"http://earthobservatory.nasa.gov/\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    earth.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initMars() {

        mars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(5);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.mars_detail;
                            String url = "<html><a href=\"http://mars.nasa.gov/\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    mars.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initJupiter() {

        jupiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(6);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.jupiter_detail;
                            String url = "<html><a href=\"https://www.nasa.gov/jupiter\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    jupiter.startAnimation(compileAnim);
                }
            }
        });
    }


    private void initSaturn() {
        saturn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(7);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.saturn_detail;
                            String url = "<html><a href=\"http://www.nasa.gov/saturn\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    saturn.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initUranus() {

        uranus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(8);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.uranus_detail;
                            String url = "<html><a href=\"http://www.nasa.gov/uranus\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    uranus.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initNeptune() {
        neptune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(9);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.neptune_detail;
                            String url = "<html><a href=\"https://www.nasa.gov/subject/3157/neptune/\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    neptune.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initPluto() {
        pluto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(10);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.pluto_detail;
                            String url = "<html><a href=\"http://www.nasa.gov/audience/forstudents/k-4/stories/nasa-knows/what-is-pluto-k4.html\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment")
                                    .commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    pluto.startAnimation(compileAnim);
                }
            }
        });
    }

    private void initMoon() {
        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentByTag("objFragment") == null &&
                        getSupportFragmentManager().findFragmentByTag("aboutFragment") == null) {
                    mediaPlayer = MediaPlayer.create(Map.this, R.raw.click_obj);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });

                    compileAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    compileAnim.setDuration(Constants.ANIMATION_DURATION);

                    compileAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
                                getSupportFragmentManager().popBackStack();
                            }
                            Cursor cursor = databaseController.getData(11);

                            String[] values = setValues(cursor);
                            int image_path = R.drawable.moon_detail;
                            String url = "<html><a href=\"http://solarsystem.nasa.gov/moon/home.cfm\">Visit NASA</a> for up-to-date info</html>";
                            ObjFragment objFragment = ObjFragment.newInstance(values, image_path, url);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.fragmentContainer, objFragment, "objFragment")
                                    .addToBackStack("objFragment").commit();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    moon.startAnimation(compileAnim);
                }
            }
        });
    }

    // Retrieve database values through an array of strings corresponding to column names and index.
    // Pull data and store it in a new array and return it

    public String[] setValues(Cursor cursor) {
        String[] array = {"name", "diameter", "mass", "distance_from_sun", "description"};
        String[] newArray = new String[5];
        for (int i = 0; i < array.length; i++) {
            String newColumn = cursor.getString(cursor.getColumnIndexOrThrow(array[i]));
            newArray[i] = newColumn;
        }
        return newArray;
    }

    // When the physical back button on the device is pressed, the fragment will be popped
    // from the back stack and it will close on the screen

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("objFragment") != null) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}






