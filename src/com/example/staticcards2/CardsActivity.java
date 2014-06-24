/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.staticcards2;

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a card scroll view with examples of different image layout cards.
 */
public final class CardsActivity extends Activity
{

    private CardScrollView mCardScroller;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardAdapter(createCards(this)));
        setContentView(mCardScroller);
    }

    /**
     * Create list of cards that showcase different type of {@link Card} API.
     */
    private List<Card> createCards(Context context) 
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(getImagesCard(context)
                .setImageLayout(ImageLayout.LEFT)
                .setText("This is full layout image format"));
        cards.add(getImagesCard(context)
                .setImageLayout(ImageLayout.FULL)
                .setText("his is left layout image format. Also," +
                		" the text size will adjust dynamically."));
        return cards;
    }

    private Card getImagesCard(Context context) 
    {
        Card card = new Card(context);
        card.addImage(R.drawable.ic_launcher);
        card.addImage(R.drawable.ic_launcher);
        card.addImage(R.drawable.ic_launcher);
        card.addImage(R.drawable.ic_launcher);
        card.addImage(R.drawable.ic_launcher);
        
        return card;
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
}
