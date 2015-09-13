/*
 * Copyright (C) 2011 Wglxy.com
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

package com.stage.openstackmonit;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * This is the About activity in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class AboutActivity extends DashboardActivity 
{

/**
 * onCreate
 *
 * Called when the activity is first created. 
 * This is where you should do all of your normal static set up: create views, bind data to lists, etc. 
 * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
 * 
 * Always followed by onStart().
 *
 * @param savedInstanceState Bundle
 */

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);

    setContentView (R.layout.activity_about);
    setTitleFromActivityLabel (R.id.title_text);

    // Tell the user something about the current display configuration.

    Configuration c = getResources ().getConfiguration ();
    int size = c.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
    //boolean isLarge = (size == Configuration.SCREENLAYOUT_SIZE_LARGE);
    //boolean isXLarge = (size == Configuration.SCREENLAYOUT_SIZE_XLARGE);
    //boolean addFrame = isLarge || isXLarge;

    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    StringBuilder sb = new StringBuilder ();
    sb.append ("Screen: ");
    sb.append (size);    
    sb.append (" density: ");
    sb.append (metrics.density);
    sb.append (" densityDpi:");
    sb.append (metrics.densityDpi);
    sb.append (" w:");
    sb.append (metrics.widthPixels);
    sb.append (" h:");
    sb.append (metrics.heightPixels);
    trace (sb.toString ());

}
    
} // end class
