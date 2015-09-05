package com.example.hannes.materialdrawertest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.MiniProfileDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends Activity implements Umgebung.OnFragmentInteractionListener, Neuigkeiten.OnFragmentInteractionListener,
Meldungen.OnFragmentInteractionListener, NeueMeldung.OnFragmentInteractionListener, Einstellungen.OnFragmentInteractionListener{


    Drawer result;
    PrimaryDrawerItem itemUmgebung = new PrimaryDrawerItem().withName("Umgebungskarte").withIdentifier(0);
    PrimaryDrawerItem itemNeuigkeiten = new PrimaryDrawerItem().withName("Neuigkeiten").withIdentifier(1);
    PrimaryDrawerItem itemMeldungen = new PrimaryDrawerItem().withName("Meine Meldungen").withIdentifier(2);
    PrimaryDrawerItem itemNeueMeldung = new PrimaryDrawerItem().withName("Meldung erstellen").withIdentifier(3);
    PrimaryDrawerItem itemEinstellungen = new PrimaryDrawerItem().withName("Einstellungen").withIdentifier(99);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable dEye = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_eye)
                .color(Color.DKGRAY)
                .sizeDp(32);

        Drawable dWrench = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_wrench)
                .color(Color.DKGRAY)
                .sizeDp(32);

        Drawable dPencil = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_pencil_square_o)
                .color(Color.DKGRAY)
                .sizeDp(32);

        Drawable dMap = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_map)
                .color(Color.DKGRAY)
                .sizeDp(32);

        Drawable dEnvelope = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_envelope_o)
                .color(Color.DKGRAY)
                .sizeDp(32);

        Drawable headerBack = getResources().getDrawable(R.drawable.header_back);


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem().withName("Hannes Innerhofer").withEmail("hannes.innerhofer@gmail.com")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .withHeaderBackground(headerBack)
                .withCompactStyle(true)
                .build();


        itemMeldungen.withIcon(dEnvelope);
        itemNeueMeldung.withIcon(dPencil);
        itemNeuigkeiten.withIcon(dEye);
        itemUmgebung.withIcon(dMap);
        itemEinstellungen.withIcon(dWrench);

        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        itemUmgebung,
                        itemNeuigkeiten,
                        itemMeldungen,
                        itemNeueMeldung,
                        new DividerDrawerItem()
                )
                .addStickyDrawerItems(itemEinstellungen)
                .withCloseOnClick(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        result.closeDrawer();
                        Fragment newFragment = null;
                        switch (drawerItem.getIdentifier()) {
                            case 0:
                                newFragment = new Umgebung();
                                break;
                            case 1:
                                newFragment = new Neuigkeiten();
                                break;
                            case 2:
                                newFragment = new Meldungen();
                                break;
                            case 3:
                                newFragment = new NeueMeldung();
                                break;
                            case 99:
                                newFragment = new Einstellungen();
                                break;
                        }

                        if (newFragment != null) {
                            // consider using Java coding conventions (upper first char class names!!!)
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                            // Replace whatever is in the fragment_container view with this fragment,
                            // and add the transaction to the back stack
                            transaction.replace(R.id.mainFrame, newFragment);
                            transaction.addToBackStack(null);

                            // Commit the transaction
                            transaction.commit();
                        }
                        return true;
                    }
                })

                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
