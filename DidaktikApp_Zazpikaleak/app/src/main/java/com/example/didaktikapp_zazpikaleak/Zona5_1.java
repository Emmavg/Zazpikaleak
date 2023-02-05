package com.example.didaktikapp_zazpikaleak;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Zona5_1 extends AppCompatActivity implements  View.OnDragListener, View.OnLongClickListener {

    private static final String TAG = Zona5_1.class.getSimpleName();
    private ImageView imgPieza1,imgPieza2,imgPieza3,imgPieza4,imgPieza5,imgPieza6;
    private View vaux,pieza1,pieza2,pieza3,pieza4,pieza5,pieza6;
    private Button btnSiguiente;
    private boolean bienPieza1,bienPieza2,bienPieza3,bienPieza4,bienPieza5,bienPieza6;
    private FloatingActionButton duda;
    private Zona5_1_DialogoDuda dialogoDuda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona5_1);
        findViews();
        implementEvents();
        btnSiguiente.setEnabled(false);

        duda = findViewById(R.id.btnDuda);

        //********************************* Boton Dudas ***************************
        duda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogoDuda = new Zona5_1_DialogoDuda();
                dialogoDuda.show(fragmentManager, "Pasos Parada");

            }
        });
    }
    public void Siguiente(View v){
        Intent i = new Intent(Zona5_1.this, Zona5_2.class);
        startActivity(i);
    }
    //Find all views and set Tag to all draggable views
    private void findViews() {
        imgPieza1 = (ImageView) findViewById(R.id.imgpieza1);
        imgPieza1.setTag("Pieza1");
        imgPieza2 = (ImageView) findViewById(R.id.imgpieza2);
        imgPieza2.setTag("Pieza2");
        imgPieza3 = (ImageView) findViewById(R.id.imgpieza3);
        imgPieza3.setTag("Pieza3");
        imgPieza4 = (ImageView) findViewById(R.id.imgpieza4);
        imgPieza4.setTag("Pieza4");
        imgPieza5 = (ImageView) findViewById(R.id.imgpieza5);
        imgPieza5.setTag("Pieza5");
        imgPieza6 = (ImageView) findViewById(R.id.imgpieza6);
        imgPieza6.setTag("Pieza6");

        pieza1 = findViewById(R.id.pieza1);
        pieza2 = findViewById(R.id.pieza2);
        pieza3 = findViewById(R.id.pieza3);
        pieza4 = findViewById(R.id.pieza4);
        pieza5 = findViewById(R.id.pieza5);
        pieza6 = findViewById(R.id.pieza6);

        btnSiguiente=findViewById(R.id.btnZona5_1_Siguiente);
        duda = findViewById(R.id.btnDuda);

    }


    //Implement long click and drag listener
    private void implementEvents() {
        //add or remove any view that you don't want to be dragged

        imgPieza1.setOnLongClickListener(this);
        imgPieza2.setOnLongClickListener(this);
        imgPieza3.setOnLongClickListener(this);
        imgPieza4.setOnLongClickListener(this);
        imgPieza5.setOnLongClickListener(this);
        imgPieza6.setOnLongClickListener(this);

        //add or remove any layout view that you don't want to accept dragged view

        findViewById(R.id.pieza1).setOnDragListener(this);
        findViewById(R.id.pieza2).setOnDragListener(this);
        findViewById(R.id.pieza3).setOnDragListener(this);
        findViewById(R.id.pieza4).setOnDragListener(this);
        findViewById(R.id.pieza5).setOnDragListener(this);
        findViewById(R.id.pieza6).setOnDragListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        // Create a new ClipData.
        // This is done in two steps to provide clarity. The convenience method
        // ClipData.newPlainText() can create a plain text ClipData in one step.

        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data//data to be dragged
                , shadowBuilder //drag shadow
                , view//local data about the drag and drop operation
                , 0//no needed flags
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
       // view.setVisibility(View.INVISIBLE);
        vaux = view;
        return true;
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.

                    //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view

                    // Invalidate the view to force a redraw in the new tint
                    //  view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a YELLOW or any color tint to the View, when the dragged view entered into drag acceptable view
                // Return true; the return value is ignored.

              //  view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;
            case DragEvent.ACTION_DRAG_EXITED:

                view.getBackground().clearColorFilter();
                view.invalidate();

                return true;
            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();


                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();
                View container =  view;//caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                System.out.println(dragData+"--------------------"+container.getTag());
                if(dragData.equals("Pieza1")){
                    if(container.getTag().toString().equals(dragData)){
                        pieza1.setBackground(getDrawable(R.drawable.zona5_1_pieza1));
                        imgPieza1.setVisibility(View.INVISIBLE);
                        bienPieza1=true;
                    }else{
                        bienPieza1=false;
                    }
                }
                if(dragData.equals("Pieza2")){
                    if(container.getTag().toString().equals(dragData)) {
                        pieza2.setBackground(getDrawable(R.drawable.zona5_1_pieza2));
                        imgPieza2.setVisibility(View.INVISIBLE);
                        bienPieza2=true;
                    }else{
                        bienPieza2=false;
                    }
                }
                if(dragData.equals("Pieza3")){
                    if(container.getTag().toString().equals(dragData)) {
                        pieza3.setBackground(getDrawable(R.drawable.zona5_1_pieza3));
                        imgPieza3.setVisibility(View.INVISIBLE);
                        bienPieza3=true;
                    }else{
                        bienPieza3=false;
                    }
                }
                if(dragData.equals("Pieza4")){
                    if(container.getTag().toString().equals(dragData)){
                        pieza4.setBackground(getDrawable(R.drawable.zona5_1_pieza4));
                        imgPieza4.setVisibility(View.INVISIBLE);
                        bienPieza4=true;
                    }else{
                        bienPieza4=false;
                    }
                }
                if(dragData.equals("Pieza5")){
                    if(container.getTag().toString().equals(dragData)) {
                        pieza5.setBackground(getDrawable(R.drawable.zona5_1_pieza5));
                        imgPieza5.setVisibility(View.INVISIBLE);
                        bienPieza5=true;
                    }else{
                        bienPieza5=false;
                    }
                }
                if(dragData.equals("Pieza6")){
                    if(container.getTag().toString().equals(dragData)) {
                        pieza6.setBackground(getDrawable(R.drawable.zona5_1_pieza6));
                        imgPieza6.setVisibility(View.INVISIBLE);
                        bienPieza6=true;
                    }else{
                        bienPieza6=false;
                    }
                }
                if(bienPieza1 && bienPieza2 && bienPieza3 && bienPieza4 && bienPieza5 && bienPieza6 ){
                    btnSiguiente.setEnabled(true);
                }




//                View v = (View) event.getLocalState();
//                ViewGroup owner = (ViewGroup) v.getParent();
//                owner.removeView(v);//remove the dragged view
//
//                container.addView(v);//Add the dragged view
//                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                // Returns true. DragEvent.getResult() will return true.
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
//                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // Does a getResult(), and displays what happened.




                // returns true; the value is ignored.
               // imgPieza1.setVisibility(view.VISIBLE);
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }


}