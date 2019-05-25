package com.serviciomecanico.serviciomecanico;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.serviciomecanico.serviciomecanico.Adaptadores.CitaAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.HerramientaAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.InventarioAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarClienteActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.Modelo.Herramienta;
import com.serviciomecanico.serviciomecanico.Modelo.Inventario;
import com.serviciomecanico.serviciomecanico.Registrar.RegisterAdministradorActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarCitaActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarClienteActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarHerramientas;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarInventario;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_clientes;
    FloatingActionButton btn_float_registrar_clientes;
    ProgressBar progressBar_visualizar_clientes;
    String idCita, idInventario, idNavegacion, idHerramienta;
    EditText search;

    //Adapter
    FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder> adapter;
    FirebaseRecyclerAdapter<Cita, CitaAdapter.ViewHolder> adapter2;
    FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder> adapter3;
    FirebaseRecyclerAdapter<Inventario, InventarioAdapter.ViewHolder> adapter4;
    FirebaseRecyclerAdapter<Herramienta, HerramientaAdapter.ViewHolder> adapter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        firebase = conexion.conexion();

        idNavegacion = "Cliente";

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        rcv_visualizar_clientes = findViewById(R.id.rcv_visualizar_clientes);
        btn_float_registrar_clientes = findViewById(R.id.btn_float_registrar_clientes);
        progressBar_visualizar_clientes = findViewById(R.id.progressBar_visualizar_clientes);
        search = findViewById(R.id.search);
        progressBar_visualizar_clientes.setVisibility(View.VISIBLE);

        //Setear al linerlayaour manager nuestro reciclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_visualizar_clientes.setLayoutManager(linearLayoutManager);

        //FirebaseUI definido para llamar de nuestro firebase la lista
        adapter = new FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder>(
                /*Clase que utilizaremos*/Cliente.class,
                /*La interfaz grafica*/R.layout.cliente,
                /*ViewHolder archivo ClienteAdapter*/ClienteAdapter.ViewHolder.class,
                /*La referencia de firebase donde buscara*/firebase.child("Cliente")
        ) {
            @Override
            protected void populateViewHolder(final ClienteAdapter.ViewHolder viewHolder, Cliente model, final int position) {
                viewHolder.txv_cliente_nombre.setText(model.getNombre());
                viewHolder.txv_cliente_correo.setText(model.getCorreo());
                viewHolder.txv_cliente_telefono.setText(model.getTelefono());
                String urlchida = model.getUrlImagen();
                Glide.with(getApplicationContext())
                        .load(urlchida)
                        .into(viewHolder.imageView_cliente);
                final String urlimagen = model.getUrlImagen();
                final String latitud = model.getLatitud();
                final String longitud = model.getLongitud();
                viewHolder.cdv_cliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarClienteActivity.class);
                        intent.putExtra("nombre",viewHolder.txv_cliente_nombre.getText().toString());
                        intent.putExtra("correo",viewHolder.txv_cliente_correo.getText().toString());
                        intent.putExtra("telefono",viewHolder.txv_cliente_telefono.getText().toString());
                        intent.putExtra("urlimagen",urlimagen);
                        intent.putExtra("latitud",latitud);
                        intent.putExtra("longitud",longitud);
                        startActivity(intent);
                    }
                });

                progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
            }
        };

        rcv_visualizar_clientes.setAdapter(adapter);

        //Scroll funcion boton
        rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    btn_float_registrar_clientes.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                    btn_float_registrar_clientes.hide();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registrar_administrador) {
            Intent intent = new Intent(MenuPrincipalActivity.this, RegisterAdministradorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clientes) {
            getSupportActionBar().setTitle("Lista de clientes");
            //FirebaseUI definido para llamar de nuestro firebase la lista
            adapter = new FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder>(
                    /*Clase que utilizaremos*/Cliente.class,
                    /*La interfaz grafica*/R.layout.cliente,
                    /*ViewHolder archivo ClienteAdapter*/ClienteAdapter.ViewHolder.class,
                    /*La referencia de firebase donde buscara*/firebase.child("Cliente")
            ) {
                @Override
                protected void populateViewHolder(final ClienteAdapter.ViewHolder viewHolder, Cliente model, final int position) {
                    viewHolder.txv_cliente_nombre.setText(model.getNombre());
                    viewHolder.txv_cliente_correo.setText(model.getCorreo());
                    viewHolder.txv_cliente_telefono.setText(model.getTelefono());
                    String urlchida = model.getUrlImagen();
                    Glide.with(getApplicationContext())
                            .load(urlchida)
                            .into(viewHolder.imageView_cliente);
                    final String urlimagen = model.getUrlImagen();
                    final String latitud = model.getLatitud();
                    final String longitud = model.getLongitud();
                    viewHolder.cdv_cliente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarClienteActivity.class);
                            intent.putExtra("nombre",viewHolder.txv_cliente_nombre.getText().toString());
                            intent.putExtra("correo",viewHolder.txv_cliente_correo.getText().toString());
                            intent.putExtra("telefono",viewHolder.txv_cliente_telefono.getText().toString());
                            intent.putExtra("urlimagen",urlimagen);
                            intent.putExtra("latitud",latitud);
                            intent.putExtra("longitud",longitud);
                            startActivity(intent);
                        }
                    });

                    progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                }
            };

            rcv_visualizar_clientes.setAdapter(adapter);

            //Scroll funcion boton
            rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        btn_float_registrar_clientes.show();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                        btn_float_registrar_clientes.hide();
                    }
                }
            });
            idNavegacion = "Cliente";
        } else if (id == R.id.nav_citas) {
            getSupportActionBar().setTitle("Lista de citas");
            idNavegacion = "Cita";
            //FirebaseUI definido para llamar de nuestro firebase la lista
            adapter2 = new FirebaseRecyclerAdapter<Cita, CitaAdapter.ViewHolder>(
                    /*Clase que utilizaremos*/Cita.class,
                    /*La interfaz grafica*/R.layout.cita,
                    /*ViewHolder archivo ClienteAdapter*/CitaAdapter.ViewHolder.class,
                    /*La referencia de firebase donde buscara*/firebase.child("Cita")
            ) {
                @Override
                protected void populateViewHolder(final CitaAdapter.ViewHolder viewHolder, Cita model,final int position) {
                    viewHolder.edt_fecha.setText(model.getFecha());
                    viewHolder.edt_hora.setText(model.getHora());
                    viewHolder.txv_nombre.setText(model.getCliente());
                    viewHolder.txv_descripcion.setText(model.getDescripcion());
                    idCita = viewHolder.txv_nombre.getText().toString();
                    final String idCitas = model.getCliente();
                    viewHolder.cdv_cita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            idCita = idCitas;
                        }
                    });

                    progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                }
            };

            rcv_visualizar_clientes.setAdapter(adapter2);

            //Scroll funcion boton
            rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        btn_float_registrar_clientes.show();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                        btn_float_registrar_clientes.hide();
                    }
                }
            });

            btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarCitaActivity.class);
                    startActivity(intent);
                }
            });
            idNavegacion = "Cita";
        } else if (id == R.id.nav_inventario) {
            getSupportActionBar().setTitle("Lista de inventario");
            idNavegacion = "Inventario";
            //FirebaseUI definido para llamar de nuestro firebase la lista
            adapter4 = new FirebaseRecyclerAdapter<Inventario, InventarioAdapter.ViewHolder>(
                    /*Clase que utilizaremos*/Inventario.class,
                    /*La interfaz grafica*/R.layout.inventario,
                    /*ViewHolder archivo ClienteAdapter*/InventarioAdapter.ViewHolder.class,
                    /*La referencia de firebase donde buscara*/firebase.child("Inventario")
            ) {
                @Override
                protected void populateViewHolder(InventarioAdapter.ViewHolder viewHolder, Inventario model, int position) {
                    viewHolder.edt_cantidad_inventario.setText("N° "+model.getCantidad());
                    viewHolder.edt_costo_inventario.setText("$ "+model.getCosto());
                    viewHolder.txv_nombre_inventario.setText(model.getNombre());
                    viewHolder.txv_descripcion_inventario.setText(model.getDescripcion());
                    idInventario = viewHolder.txv_nombre_inventario.getText().toString();
                    final String idInventarios = model.getNombre();
                    viewHolder.cdv_inventario.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            idInventario = idInventarios;
                        }
                    });

                    progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                }
            };

            rcv_visualizar_clientes.setAdapter(adapter4);

            //Scroll funcion boton
            rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        btn_float_registrar_clientes.show();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                        btn_float_registrar_clientes.hide();
                    }
                }
            });

            btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarInventario.class);
                    startActivity(intent);
                }
            });
            idNavegacion = "Inventario";
        } else if (id == R.id.nav_herramientas) {
            getSupportActionBar().setTitle("Lista de herramientas");
            idNavegacion = "Herramienta";
            //FirebaseUI definido para llamar de nuestro firebase la lista
            adapter5 = new FirebaseRecyclerAdapter<Herramienta, HerramientaAdapter.ViewHolder>(
                    /*Clase que utilizaremos*/Herramienta.class,
                    /*La interfaz grafica*/R.layout.herramienta,
                    /*ViewHolder archivo ClienteAdapter*/HerramientaAdapter.ViewHolder.class,
                    /*La referencia de firebase donde buscara*/firebase.child("Herramienta")
            ) {
                @Override
                protected void populateViewHolder(HerramientaAdapter.ViewHolder viewHolder, Herramienta model, int position) {
                    viewHolder.edt_marca_herramienta.setText(model.getMarca());
                    viewHolder.edt_cantidad_herramienta.setText("N° "+model.getCantidad());
                    viewHolder.txv_nombre_herramienta.setText(model.getNombre());
                    viewHolder.txv_descripcion_herramienta.setText(model.getDescripcion());
                    idInventario = viewHolder.txv_nombre_herramienta.getText().toString();
                    final String idHerramientas = model.getNombre();
                    viewHolder.cdv_herramienta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            idHerramienta = idHerramientas;
                        }
                    });

                    progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                }
            };

            rcv_visualizar_clientes.setAdapter(adapter5);

            //Scroll funcion boton
            rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        btn_float_registrar_clientes.show();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                        btn_float_registrar_clientes.hide();
                    }
                }
            });

            btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarHerramientas.class);
                    startActivity(intent);
                }
            });
            idNavegacion = "Herramienta";
        } else if (id == R.id.nav_cerrar_sesion) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void btn_float_registrar_clientes (View view){
        Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarClienteActivity.class);
        startActivity(intent);
    }

    public void btn_eliminar_cita(View view){

        AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
        eliminar.setMessage("¿Desea eliminar esta cita?");
        eliminar.setTitle("Eliminar cita");
        eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebase.child("Cita").child(idCita).removeValue();
            }
        });

        eliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = eliminar.create();
        dialog.show();
    }

    public void btn_eliminar_inventario(View view){

        AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
        eliminar.setMessage("¿Desea eliminar este inventario?");
        eliminar.setTitle("Eliminar inventario");
        eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebase.child("Inventario").child(idInventario).removeValue();
            }
        });

        eliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = eliminar.create();
        dialog.show();
    }

    public void btn_eliminar_herramienta(View view){

        AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
        eliminar.setMessage("¿Desea eliminar esta herramienta?");
        eliminar.setTitle("Eliminar herramienta");
        eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebase.child("Herramienta").child(idHerramienta).removeValue();
            }
        });

        eliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = eliminar.create();
        dialog.show();
    }

    public void buscarCliente(View view) {
        switch (idNavegacion) {
            case "Cliente":
                String text = search.getText().toString();
                Query fireBaseSearch = firebase.child("Cliente").orderByChild("nombre").startAt(text).endAt(text + "\uf8ff");
                adapter3 = new FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder>(
                        /*Clase que utilizaremos*/Cliente.class,
                        /*La interfaz grafica*/R.layout.cliente,
                        /*ViewHolder archivo ClienteAdapter*/ClienteAdapter.ViewHolder.class,
                        /*La referencia de firebase donde buscara*/fireBaseSearch
                ) {
                    @Override
                    protected void populateViewHolder(final ClienteAdapter.ViewHolder viewHolder, Cliente model, final int position) {
                        viewHolder.txv_cliente_nombre.setText(model.getNombre());
                        viewHolder.txv_cliente_correo.setText(model.getCorreo());
                        viewHolder.txv_cliente_telefono.setText(model.getTelefono());
                        String urlchida = model.getUrlImagen();
                        Glide.with(getApplicationContext())
                                .load(urlchida)
                                .into(viewHolder.imageView_cliente);
                        final String urlimagen = model.getUrlImagen();
                        final String latitud = model.getLatitud();
                        final String longitud = model.getLongitud();
                        viewHolder.cdv_cliente.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarClienteActivity.class);
                                intent.putExtra("nombre", viewHolder.txv_cliente_nombre.getText().toString());
                                intent.putExtra("correo", viewHolder.txv_cliente_correo.getText().toString());
                                intent.putExtra("telefono", viewHolder.txv_cliente_telefono.getText().toString());
                                intent.putExtra("urlimagen", urlimagen);
                                intent.putExtra("latitud", latitud);
                                intent.putExtra("longitud", longitud);
                                startActivity(intent);
                            }
                        });

                        progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                    }
                };

                rcv_visualizar_clientes.setAdapter(adapter3);

                //Scroll funcion boton
                rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            btn_float_registrar_clientes.show();
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 0 || dy < 0 && btn_float_registrar_clientes.isShown()) {
                            btn_float_registrar_clientes.hide();
                        }
                    }
                });
                break;
            case "Cita":
                getSupportActionBar().setTitle("Lista de citas");
                text = search.getText().toString();
                fireBaseSearch = firebase.child("Cita").orderByChild("cliente").startAt(text).endAt(text + "\uf8ff");
                //FirebaseUI definido para llamar de nuestro firebase la lista
                adapter2 = new FirebaseRecyclerAdapter<Cita, CitaAdapter.ViewHolder>(
                        /*Clase que utilizaremos*/Cita.class,
                        /*La interfaz grafica*/R.layout.cita,
                        /*ViewHolder archivo ClienteAdapter*/CitaAdapter.ViewHolder.class,
                        /*La referencia de firebase donde buscara*/fireBaseSearch
                ) {
                    @Override
                    protected void populateViewHolder(final CitaAdapter.ViewHolder viewHolder, Cita model,final int position) {
                        viewHolder.edt_fecha.setText(model.getFecha());
                        viewHolder.edt_hora.setText(model.getHora());
                        viewHolder.txv_nombre.setText(model.getCliente());
                        viewHolder.txv_descripcion.setText(model.getDescripcion());
                        idCita = viewHolder.txv_nombre.getText().toString();
                        final String idCitas = model.getCliente();
                        viewHolder.cdv_cita.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                idCita = idCitas;
                            }
                        });

                        progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                    }
                };

                rcv_visualizar_clientes.setAdapter(adapter2);

                //Scroll funcion boton
                rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState == RecyclerView.SCROLL_STATE_IDLE){
                            btn_float_registrar_clientes.show();
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                            btn_float_registrar_clientes.hide();
                        }
                    }
                });

                btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarCitaActivity.class);
                        startActivity(intent);
                    }
                });
                break;
                case "Inventario":
                    getSupportActionBar().setTitle("Lista de inventario");
                    text = search.getText().toString();
                    fireBaseSearch = firebase.child("Inventario").orderByChild("nombre").startAt(text).endAt(text + "\uf8ff");
                    //FirebaseUI definido para llamar de nuestro firebase la lista
                    adapter4 = new FirebaseRecyclerAdapter<Inventario, InventarioAdapter.ViewHolder>(
                            /*Clase que utilizaremos*/Inventario.class,
                            /*La interfaz grafica*/R.layout.inventario,
                            /*ViewHolder archivo ClienteAdapter*/InventarioAdapter.ViewHolder.class,
                            /*La referencia de firebase donde buscara*/fireBaseSearch
                    ) {
                        @Override
                        protected void populateViewHolder(InventarioAdapter.ViewHolder viewHolder, Inventario model, int position) {
                            viewHolder.edt_cantidad_inventario.setText("N° "+model.getCantidad());
                            viewHolder.edt_costo_inventario.setText("$ "+model.getCosto());
                            viewHolder.txv_nombre_inventario.setText(model.getNombre());
                            viewHolder.txv_descripcion_inventario.setText(model.getDescripcion());
                            idInventario = viewHolder.txv_nombre_inventario.getText().toString();
                            final String idInventarios = model.getNombre();
                            viewHolder.cdv_inventario.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    idInventario = idInventarios;
                                }
                            });

                            progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                        }
                    };

                    rcv_visualizar_clientes.setAdapter(adapter4);

                    //Scroll funcion boton
                    rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if(newState == RecyclerView.SCROLL_STATE_IDLE){
                                btn_float_registrar_clientes.show();
                            }
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                                btn_float_registrar_clientes.hide();
                            }
                        }
                    });

                    btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarInventario.class);
                            startActivity(intent);
                        }
                    });
            break;
            case "Herramienta":
                getSupportActionBar().setTitle("Lista de herramientas");
                text = search.getText().toString();
                fireBaseSearch = firebase.child("Herramienta").orderByChild("nombre").startAt(text).endAt(text + "\uf8ff");
                //FirebaseUI definido para llamar de nuestro firebase la lista
                adapter5 = new FirebaseRecyclerAdapter<Herramienta, HerramientaAdapter.ViewHolder>(
                        /*Clase que utilizaremos*/Herramienta.class,
                        /*La interfaz grafica*/R.layout.herramienta,
                        /*ViewHolder archivo ClienteAdapter*/HerramientaAdapter.ViewHolder.class,
                        /*La referencia de firebase donde buscara*/fireBaseSearch
                ) {
                    @Override
                    protected void populateViewHolder(HerramientaAdapter.ViewHolder viewHolder, Herramienta model, int position) {
                        viewHolder.edt_marca_herramienta.setText(model.getMarca());
                        viewHolder.edt_cantidad_herramienta.setText("N° "+model.getCantidad());
                        viewHolder.txv_nombre_herramienta.setText(model.getNombre());
                        viewHolder.txv_descripcion_herramienta.setText(model.getDescripcion());
                        idInventario = viewHolder.txv_nombre_herramienta.getText().toString();
                        final String idHerramientas = model.getNombre();
                        viewHolder.cdv_herramienta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                idHerramienta = idHerramientas;
                            }
                        });

                        progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                    }
                };

                rcv_visualizar_clientes.setAdapter(adapter5);

                //Scroll funcion boton
                rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState == RecyclerView.SCROLL_STATE_IDLE){
                            btn_float_registrar_clientes.show();
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                            btn_float_registrar_clientes.hide();
                        }
                    }
                });

                btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarHerramientas.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
        }
    }
}
