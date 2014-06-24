package com.example.staticcards2;

import java.util.ArrayList;
import java.util.List;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.os.Build;

public class MainActivity extends Activity
{

    private static final String TAG = MainActivity.class.getSimpleName();

    // Index of api demo cards.
    // Visible for testing.
    static final int CARDS = 0;
    static final int VOICE_MENU = 1;
    static final int ANIMATION1 = 2;
    static final int ANIMATION2 = 3;
    static final int ANIMATION3 = 4;

    private CardScrollAdapter mAdapter;
    private CardScrollView mCardScroller;

    // Visible for testing.
    CardScrollView getScroller() 
    {
        return mCardScroller;
    }

    @Override
    protected void onCreate(Bundle bundle) 
    {
        super.onCreate(bundle);

        mAdapter = new CardAdapter(createCards(this));
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(mAdapter);
        setContentView(mCardScroller);
        setCardScrollerListener();
    }

    /**
     * Create list of API demo cards.
     */
    private List<Card> createCards(Context context) 
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(CARDS, new Card(context).setText("Card API"));
        cards.add(VOICE_MENU, new Card(context).setText("Voice menu"));
        return cards;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() 
    {
        mCardScroller.deactivate();
        super.onPause();
    }

    /**
     * Different type of activities can be shown, when tapped on a card.
     */
    private void setCardScrollerListener()
    {
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Clicked view at position " + position + ", row-id " + id);
                int soundEffect = Sounds.TAP;
                switch (position) 
                {
                    case CARDS:
                        startActivity(new Intent(MainActivity.this, CardsActivity.class));
                        break;

    /*               case GESTURE_DETECTOR:
                        startActivity(new Intent(MainActivity.this,
                                SelectGestureDemoActivity.class));
                        break;

                    case THEMING:
                        startActivity(new Intent(MainActivity.this, ThemingActivity.class));
                        break;

                    case OPENGL:
                        startService(new Intent(MainActivity.this, OpenGlService.class));
                        break;*/

                    case VOICE_MENU:
                        startActivity(new Intent(MainActivity.this, VoiceMenuActivity.class));
                        break;
                        
                   /* case ANIMATION1:
                        startActivity(new Intent(MainActivity.this, Game.class));
                        break;
                    case ANIMATION2:
                        startActivity(new Intent(MainActivity.this, Game1.class));
                        break;
                    case ANIMATION3:
                        startActivity(new Intent(MainActivity.this, Game2.class));
                        break;*/
    
                    default:
                        soundEffect = Sounds.ERROR;
                        Log.d(TAG, "Don't show anything");
                }

                // Play sound.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(soundEffect);
            }
        });
    }
}
