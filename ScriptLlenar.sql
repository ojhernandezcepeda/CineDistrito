PGDMP         	                x            CineDistrito    12.3    12.3 �               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            
           1262    34861    CineDistrito    DATABASE     �   CREATE DATABASE "CineDistrito" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE "CineDistrito";
                admin    false            �            1259    34864    boletabonus    TABLE     �   CREATE TABLE public.boletabonus (
    id_boletabonus integer NOT NULL,
    cliente_id integer NOT NULL,
    fecha date NOT NULL,
    fechalimite date NOT NULL
);
    DROP TABLE public.boletabonus;
       public         heap    admin    false            �            1259    34862    boletabonus_id_boletabonus_seq    SEQUENCE     �   CREATE SEQUENCE public.boletabonus_id_boletabonus_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.boletabonus_id_boletabonus_seq;
       public          admin    false    203                       0    0    boletabonus_id_boletabonus_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.boletabonus_id_boletabonus_seq OWNED BY public.boletabonus.id_boletabonus;
          public          admin    false    202            �            1259    34870    calificacion    TABLE     �   CREATE TABLE public.calificacion (
    id_calificacion integer NOT NULL,
    puntuacion integer NOT NULL,
    pelicula_id integer,
    snack_id integer
);
     DROP TABLE public.calificacion;
       public         heap    admin    false            �            1259    34868     calificacion_id_calificacion_seq    SEQUENCE     �   CREATE SEQUENCE public.calificacion_id_calificacion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.calificacion_id_calificacion_seq;
       public          admin    false    205                       0    0     calificacion_id_calificacion_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.calificacion_id_calificacion_seq OWNED BY public.calificacion.id_calificacion;
          public          admin    false    204            �            1259    34876    cliente    TABLE     �  CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nombre character varying(50) NOT NULL,
    documento character varying(50) NOT NULL,
    tipodocumento character varying(50) NOT NULL,
    telefono character varying(50) NOT NULL,
    "contraseña" character varying(50) NOT NULL,
    maxsillas integer NOT NULL,
    totalpuntos integer NOT NULL,
    usuario character varying(50) NOT NULL
);
    DROP TABLE public.cliente;
       public         heap    admin    false            �            1259    34874    cliente_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_id_cliente_seq;
       public          admin    false    207                       0    0    cliente_id_cliente_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;
          public          admin    false    206            �            1259    34882    compraboletas    TABLE     �   CREATE TABLE public.compraboletas (
    id_compraboletas integer NOT NULL,
    fecha date NOT NULL,
    cantidadbonus integer NOT NULL,
    empleado_id integer NOT NULL,
    cliente_id integer
);
 !   DROP TABLE public.compraboletas;
       public         heap    admin    false            �            1259    34880 "   compraboletas_id_compraboletas_seq    SEQUENCE     �   CREATE SEQUENCE public.compraboletas_id_compraboletas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.compraboletas_id_compraboletas_seq;
       public          admin    false    209                       0    0 "   compraboletas_id_compraboletas_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.compraboletas_id_compraboletas_seq OWNED BY public.compraboletas.id_compraboletas;
          public          admin    false    208            �            1259    34888    comprasnacks    TABLE     �   CREATE TABLE public.comprasnacks (
    id_comprasnacks integer NOT NULL,
    fecha date NOT NULL,
    cliente_id integer,
    empleado_id integer NOT NULL
);
     DROP TABLE public.comprasnacks;
       public         heap    admin    false            �            1259    34886     comprasnacks_id_comprasnacks_seq    SEQUENCE     �   CREATE SEQUENCE public.comprasnacks_id_comprasnacks_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.comprasnacks_id_comprasnacks_seq;
       public          admin    false    211                       0    0     comprasnacks_id_comprasnacks_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.comprasnacks_id_comprasnacks_seq OWNED BY public.comprasnacks.id_comprasnacks;
          public          admin    false    210            �            1259    34894    comprasnacks_snack    TABLE     �   CREATE TABLE public.comprasnacks_snack (
    id_comprasnacks_snack integer NOT NULL,
    snack_id integer NOT NULL,
    comprasnacks_id integer NOT NULL
);
 &   DROP TABLE public.comprasnacks_snack;
       public         heap    admin    false            �            1259    34892 ,   comprasnacks_snack_id_comprasnacks_snack_seq    SEQUENCE     �   CREATE SEQUENCE public.comprasnacks_snack_id_comprasnacks_snack_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.comprasnacks_snack_id_comprasnacks_snack_seq;
       public          admin    false    213                       0    0 ,   comprasnacks_snack_id_comprasnacks_snack_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.comprasnacks_snack_id_comprasnacks_snack_seq OWNED BY public.comprasnacks_snack.id_comprasnacks_snack;
          public          admin    false    212            �            1259    34900    empleado    TABLE       CREATE TABLE public.empleado (
    id_empleado integer NOT NULL,
    codigo character varying(50) NOT NULL,
    nombre character varying(50) NOT NULL,
    cargo character varying(50) NOT NULL,
    documento character varying(50) NOT NULL,
    tipodocumento character varying(50) NOT NULL,
    telefono character varying(50) NOT NULL,
    "contraseña" character varying(50) NOT NULL,
    establecimiento_id integer,
    fechainiciocontrato date NOT NULL,
    salario numeric(10,2) NOT NULL,
    estado character varying(50) NOT NULL
);
    DROP TABLE public.empleado;
       public         heap    admin    false            �            1259    34898    empleado_id_empleado_seq    SEQUENCE     �   CREATE SEQUENCE public.empleado_id_empleado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.empleado_id_empleado_seq;
       public          admin    false    215                       0    0    empleado_id_empleado_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.empleado_id_empleado_seq OWNED BY public.empleado.id_empleado;
          public          admin    false    214            �            1259    34906    establecimiento    TABLE     &  CREATE TABLE public.establecimiento (
    id_establecimiento integer NOT NULL,
    nombre character varying(50) NOT NULL,
    ubicacion character varying(50) NOT NULL,
    max_empleados integer NOT NULL,
    tipoestablecimiento_id integer NOT NULL,
    estado character varying(50) NOT NULL
);
 #   DROP TABLE public.establecimiento;
       public         heap    admin    false            �            1259    34904 &   establecimiento_id_establecimiento_seq    SEQUENCE     �   CREATE SEQUENCE public.establecimiento_id_establecimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.establecimiento_id_establecimiento_seq;
       public          admin    false    217                       0    0 &   establecimiento_id_establecimiento_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.establecimiento_id_establecimiento_seq OWNED BY public.establecimiento.id_establecimiento;
          public          admin    false    216            �            1259    34912    historialboletas    TABLE     �  CREATE TABLE public.historialboletas (
    id_historialboletas integer NOT NULL,
    compra_id integer NOT NULL,
    cantidadboletas integer NOT NULL,
    valortotal numeric(10,2) NOT NULL,
    cantidadpreferencial integer NOT NULL,
    cantidadgeneral integer NOT NULL,
    fechaventa date NOT NULL,
    nombrepelicula character varying(200) NOT NULL,
    generopelicula character varying(50) NOT NULL,
    empleado_id integer NOT NULL
);
 $   DROP TABLE public.historialboletas;
       public         heap    admin    false            �            1259    34910 (   historialboletas_id_historialboletas_seq    SEQUENCE     �   CREATE SEQUENCE public.historialboletas_id_historialboletas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE public.historialboletas_id_historialboletas_seq;
       public          admin    false    219                       0    0 (   historialboletas_id_historialboletas_seq    SEQUENCE OWNED BY     u   ALTER SEQUENCE public.historialboletas_id_historialboletas_seq OWNED BY public.historialboletas.id_historialboletas;
          public          admin    false    218            �            1259    34920    historialcargos    TABLE     �  CREATE TABLE public.historialcargos (
    id_historialcargos integer NOT NULL,
    empleado_id integer NOT NULL,
    codigo character varying(50) NOT NULL,
    cargo character varying(50) NOT NULL,
    fechainiciocontrato date NOT NULL,
    nombreestablecimiento character varying(50) NOT NULL,
    establecimiento_id integer NOT NULL,
    salario numeric(10,2) NOT NULL,
    fechafincontrato date
);
 #   DROP TABLE public.historialcargos;
       public         heap    admin    false            �            1259    34918    historialcargos_empleado_id_seq    SEQUENCE     �   CREATE SEQUENCE public.historialcargos_empleado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.historialcargos_empleado_id_seq;
       public          admin    false    222                       0    0    historialcargos_empleado_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.historialcargos_empleado_id_seq OWNED BY public.historialcargos.empleado_id;
          public          admin    false    221            �            1259    34916 &   historialcargos_id_historialcargos_seq    SEQUENCE     �   CREATE SEQUENCE public.historialcargos_id_historialcargos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.historialcargos_id_historialcargos_seq;
       public          admin    false    222                       0    0 &   historialcargos_id_historialcargos_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.historialcargos_id_historialcargos_seq OWNED BY public.historialcargos.id_historialcargos;
          public          admin    false    220            �            1259    34927    historialsnacks    TABLE     (  CREATE TABLE public.historialsnacks (
    id_historialsnacks integer NOT NULL,
    comprasnack_id integer NOT NULL,
    totalsnacks integer NOT NULL,
    valortotal numeric(10,2) NOT NULL,
    establecimiento_id integer NOT NULL,
    empleado_id integer NOT NULL,
    fechaventa date NOT NULL
);
 #   DROP TABLE public.historialsnacks;
       public         heap    admin    false            �            1259    34925 &   historialsnacks_id_historialsnacks_seq    SEQUENCE     �   CREATE SEQUENCE public.historialsnacks_id_historialsnacks_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.historialsnacks_id_historialsnacks_seq;
       public          admin    false    224                       0    0 &   historialsnacks_id_historialsnacks_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.historialsnacks_id_historialsnacks_seq OWNED BY public.historialsnacks.id_historialsnacks;
          public          admin    false    223            �            1259    34933    inventariogeneral    TABLE     �   CREATE TABLE public.inventariogeneral (
    id_snack integer NOT NULL,
    nombre character varying(50) NOT NULL,
    stock integer NOT NULL,
    valorunidad numeric(8,2) NOT NULL,
    puntos integer NOT NULL,
    fechavencimiento date NOT NULL
);
 %   DROP TABLE public.inventariogeneral;
       public         heap    admin    false            �            1259    34931    inventariogeneral_id_snack_seq    SEQUENCE     �   CREATE SEQUENCE public.inventariogeneral_id_snack_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.inventariogeneral_id_snack_seq;
       public          admin    false    226                       0    0    inventariogeneral_id_snack_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.inventariogeneral_id_snack_seq OWNED BY public.inventariogeneral.id_snack;
          public          admin    false    225            �            1259    34939    pelicula    TABLE     B  CREATE TABLE public.pelicula (
    id_pelicula integer NOT NULL,
    nombre character varying(200) NOT NULL,
    duracion integer NOT NULL,
    genero character varying(50) NOT NULL,
    fechainicio date NOT NULL,
    horainicio integer NOT NULL,
    sala_id integer NOT NULL,
    estado character varying(50) NOT NULL
);
    DROP TABLE public.pelicula;
       public         heap    admin    false            �            1259    34937    pelicula_id_pelicula_seq    SEQUENCE     �   CREATE SEQUENCE public.pelicula_id_pelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.pelicula_id_pelicula_seq;
       public          admin    false    228                       0    0    pelicula_id_pelicula_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.pelicula_id_pelicula_seq OWNED BY public.pelicula.id_pelicula;
          public          admin    false    227            �            1259    34945    pelicula_silla    TABLE     �   CREATE TABLE public.pelicula_silla (
    id_pelicula_silla integer NOT NULL,
    pelicula_id integer NOT NULL,
    silla_id integer NOT NULL,
    compra_id integer,
    reserva_id integer
);
 "   DROP TABLE public.pelicula_silla;
       public         heap    admin    false            �            1259    34943 $   pelicula_silla_id_pelicula_silla_seq    SEQUENCE     �   CREATE SEQUENCE public.pelicula_silla_id_pelicula_silla_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.pelicula_silla_id_pelicula_silla_seq;
       public          admin    false    230                       0    0 $   pelicula_silla_id_pelicula_silla_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.pelicula_silla_id_pelicula_silla_seq OWNED BY public.pelicula_silla.id_pelicula_silla;
          public          admin    false    229            �            1259    34951    reservaspelicula    TABLE     �   CREATE TABLE public.reservaspelicula (
    id_reserva integer NOT NULL,
    cliente_id integer NOT NULL,
    fecha date NOT NULL,
    estado character varying(50) NOT NULL,
    valortotal numeric(10,2) NOT NULL,
    puntostotal integer NOT NULL
);
 $   DROP TABLE public.reservaspelicula;
       public         heap    admin    false            �            1259    34949    reservaspelicula_id_reserva_seq    SEQUENCE     �   CREATE SEQUENCE public.reservaspelicula_id_reserva_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.reservaspelicula_id_reserva_seq;
       public          admin    false    232                       0    0    reservaspelicula_id_reserva_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.reservaspelicula_id_reserva_seq OWNED BY public.reservaspelicula.id_reserva;
          public          admin    false    231            �            1259    34957    sala    TABLE       CREATE TABLE public.sala (
    id_sala integer NOT NULL,
    cantidadgeneral integer NOT NULL,
    cantidadpreferencial integer NOT NULL,
    numerosala integer NOT NULL,
    establecimiento_id integer NOT NULL,
    estado character varying(50) NOT NULL
);
    DROP TABLE public.sala;
       public         heap    admin    false            �            1259    34955    sala_id_sala_seq    SEQUENCE     �   CREATE SEQUENCE public.sala_id_sala_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.sala_id_sala_seq;
       public          admin    false    234                       0    0    sala_id_sala_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.sala_id_sala_seq OWNED BY public.sala.id_sala;
          public          admin    false    233            �            1259    34963    silla    TABLE     �   CREATE TABLE public.silla (
    id_silla integer NOT NULL,
    fila character varying(50) NOT NULL,
    columna integer NOT NULL,
    tiposilla_id integer NOT NULL
);
    DROP TABLE public.silla;
       public         heap    admin    false            �            1259    34961    silla_id_silla_seq    SEQUENCE     �   CREATE SEQUENCE public.silla_id_silla_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.silla_id_silla_seq;
       public          admin    false    236                       0    0    silla_id_silla_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.silla_id_silla_seq OWNED BY public.silla.id_silla;
          public          admin    false    235            �            1259    34969    snack    TABLE     �   CREATE TABLE public.snack (
    id_snacks integer NOT NULL,
    establecimiento_id integer NOT NULL,
    stockestablecimiento integer NOT NULL,
    inventariogeneral_id integer NOT NULL
);
    DROP TABLE public.snack;
       public         heap    admin    false            �            1259    34967    snack_id_snacks_seq    SEQUENCE     �   CREATE SEQUENCE public.snack_id_snacks_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.snack_id_snacks_seq;
       public          admin    false    238                       0    0    snack_id_snacks_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.snack_id_snacks_seq OWNED BY public.snack.id_snacks;
          public          admin    false    237            �            1259    34975    tipoestablecimiento    TABLE     �   CREATE TABLE public.tipoestablecimiento (
    id_tipoestablecimiento integer NOT NULL,
    tipo character varying(50) NOT NULL
);
 '   DROP TABLE public.tipoestablecimiento;
       public         heap    admin    false            �            1259    34973 .   tipoestablecimiento_id_tipoestablecimiento_seq    SEQUENCE     �   CREATE SEQUENCE public.tipoestablecimiento_id_tipoestablecimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.tipoestablecimiento_id_tipoestablecimiento_seq;
       public          admin    false    240                       0    0 .   tipoestablecimiento_id_tipoestablecimiento_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.tipoestablecimiento_id_tipoestablecimiento_seq OWNED BY public.tipoestablecimiento.id_tipoestablecimiento;
          public          admin    false    239            �            1259    34981 	   tiposilla    TABLE     �   CREATE TABLE public.tiposilla (
    id_tiposilla integer NOT NULL,
    nombre character varying(50) NOT NULL,
    precio numeric(8,2) NOT NULL,
    puntos integer NOT NULL
);
    DROP TABLE public.tiposilla;
       public         heap    admin    false            �            1259    34979    tiposilla_id_tiposilla_seq    SEQUENCE     �   CREATE SEQUENCE public.tiposilla_id_tiposilla_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.tiposilla_id_tiposilla_seq;
       public          admin    false    242                       0    0    tiposilla_id_tiposilla_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.tiposilla_id_tiposilla_seq OWNED BY public.tiposilla.id_tiposilla;
          public          admin    false    241            �
           2604    34867    boletabonus id_boletabonus    DEFAULT     �   ALTER TABLE ONLY public.boletabonus ALTER COLUMN id_boletabonus SET DEFAULT nextval('public.boletabonus_id_boletabonus_seq'::regclass);
 I   ALTER TABLE public.boletabonus ALTER COLUMN id_boletabonus DROP DEFAULT;
       public          admin    false    203    202    203            �
           2604    34873    calificacion id_calificacion    DEFAULT     �   ALTER TABLE ONLY public.calificacion ALTER COLUMN id_calificacion SET DEFAULT nextval('public.calificacion_id_calificacion_seq'::regclass);
 K   ALTER TABLE public.calificacion ALTER COLUMN id_calificacion DROP DEFAULT;
       public          admin    false    204    205    205            �
           2604    34879    cliente id_cliente    DEFAULT     x   ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN id_cliente DROP DEFAULT;
       public          admin    false    207    206    207            �
           2604    34885    compraboletas id_compraboletas    DEFAULT     �   ALTER TABLE ONLY public.compraboletas ALTER COLUMN id_compraboletas SET DEFAULT nextval('public.compraboletas_id_compraboletas_seq'::regclass);
 M   ALTER TABLE public.compraboletas ALTER COLUMN id_compraboletas DROP DEFAULT;
       public          admin    false    209    208    209            �
           2604    34891    comprasnacks id_comprasnacks    DEFAULT     �   ALTER TABLE ONLY public.comprasnacks ALTER COLUMN id_comprasnacks SET DEFAULT nextval('public.comprasnacks_id_comprasnacks_seq'::regclass);
 K   ALTER TABLE public.comprasnacks ALTER COLUMN id_comprasnacks DROP DEFAULT;
       public          admin    false    211    210    211            �
           2604    34897 (   comprasnacks_snack id_comprasnacks_snack    DEFAULT     �   ALTER TABLE ONLY public.comprasnacks_snack ALTER COLUMN id_comprasnacks_snack SET DEFAULT nextval('public.comprasnacks_snack_id_comprasnacks_snack_seq'::regclass);
 W   ALTER TABLE public.comprasnacks_snack ALTER COLUMN id_comprasnacks_snack DROP DEFAULT;
       public          admin    false    213    212    213            �
           2604    34903    empleado id_empleado    DEFAULT     |   ALTER TABLE ONLY public.empleado ALTER COLUMN id_empleado SET DEFAULT nextval('public.empleado_id_empleado_seq'::regclass);
 C   ALTER TABLE public.empleado ALTER COLUMN id_empleado DROP DEFAULT;
       public          admin    false    215    214    215            �
           2604    34909 "   establecimiento id_establecimiento    DEFAULT     �   ALTER TABLE ONLY public.establecimiento ALTER COLUMN id_establecimiento SET DEFAULT nextval('public.establecimiento_id_establecimiento_seq'::regclass);
 Q   ALTER TABLE public.establecimiento ALTER COLUMN id_establecimiento DROP DEFAULT;
       public          admin    false    216    217    217            �
           2604    34915 $   historialboletas id_historialboletas    DEFAULT     �   ALTER TABLE ONLY public.historialboletas ALTER COLUMN id_historialboletas SET DEFAULT nextval('public.historialboletas_id_historialboletas_seq'::regclass);
 S   ALTER TABLE public.historialboletas ALTER COLUMN id_historialboletas DROP DEFAULT;
       public          admin    false    219    218    219            �
           2604    34923 "   historialcargos id_historialcargos    DEFAULT     �   ALTER TABLE ONLY public.historialcargos ALTER COLUMN id_historialcargos SET DEFAULT nextval('public.historialcargos_id_historialcargos_seq'::regclass);
 Q   ALTER TABLE public.historialcargos ALTER COLUMN id_historialcargos DROP DEFAULT;
       public          admin    false    222    220    222            �
           2604    34924    historialcargos empleado_id    DEFAULT     �   ALTER TABLE ONLY public.historialcargos ALTER COLUMN empleado_id SET DEFAULT nextval('public.historialcargos_empleado_id_seq'::regclass);
 J   ALTER TABLE public.historialcargos ALTER COLUMN empleado_id DROP DEFAULT;
       public          admin    false    221    222    222            �
           2604    34930 "   historialsnacks id_historialsnacks    DEFAULT     �   ALTER TABLE ONLY public.historialsnacks ALTER COLUMN id_historialsnacks SET DEFAULT nextval('public.historialsnacks_id_historialsnacks_seq'::regclass);
 Q   ALTER TABLE public.historialsnacks ALTER COLUMN id_historialsnacks DROP DEFAULT;
       public          admin    false    223    224    224            �
           2604    34936    inventariogeneral id_snack    DEFAULT     �   ALTER TABLE ONLY public.inventariogeneral ALTER COLUMN id_snack SET DEFAULT nextval('public.inventariogeneral_id_snack_seq'::regclass);
 I   ALTER TABLE public.inventariogeneral ALTER COLUMN id_snack DROP DEFAULT;
       public          admin    false    226    225    226                        2604    34942    pelicula id_pelicula    DEFAULT     |   ALTER TABLE ONLY public.pelicula ALTER COLUMN id_pelicula SET DEFAULT nextval('public.pelicula_id_pelicula_seq'::regclass);
 C   ALTER TABLE public.pelicula ALTER COLUMN id_pelicula DROP DEFAULT;
       public          admin    false    228    227    228                       2604    34948     pelicula_silla id_pelicula_silla    DEFAULT     �   ALTER TABLE ONLY public.pelicula_silla ALTER COLUMN id_pelicula_silla SET DEFAULT nextval('public.pelicula_silla_id_pelicula_silla_seq'::regclass);
 O   ALTER TABLE public.pelicula_silla ALTER COLUMN id_pelicula_silla DROP DEFAULT;
       public          admin    false    230    229    230                       2604    34954    reservaspelicula id_reserva    DEFAULT     �   ALTER TABLE ONLY public.reservaspelicula ALTER COLUMN id_reserva SET DEFAULT nextval('public.reservaspelicula_id_reserva_seq'::regclass);
 J   ALTER TABLE public.reservaspelicula ALTER COLUMN id_reserva DROP DEFAULT;
       public          admin    false    232    231    232                       2604    34960    sala id_sala    DEFAULT     l   ALTER TABLE ONLY public.sala ALTER COLUMN id_sala SET DEFAULT nextval('public.sala_id_sala_seq'::regclass);
 ;   ALTER TABLE public.sala ALTER COLUMN id_sala DROP DEFAULT;
       public          admin    false    234    233    234                       2604    34966    silla id_silla    DEFAULT     p   ALTER TABLE ONLY public.silla ALTER COLUMN id_silla SET DEFAULT nextval('public.silla_id_silla_seq'::regclass);
 =   ALTER TABLE public.silla ALTER COLUMN id_silla DROP DEFAULT;
       public          admin    false    235    236    236                       2604    34972    snack id_snacks    DEFAULT     r   ALTER TABLE ONLY public.snack ALTER COLUMN id_snacks SET DEFAULT nextval('public.snack_id_snacks_seq'::regclass);
 >   ALTER TABLE public.snack ALTER COLUMN id_snacks DROP DEFAULT;
       public          admin    false    237    238    238                       2604    34978 *   tipoestablecimiento id_tipoestablecimiento    DEFAULT     �   ALTER TABLE ONLY public.tipoestablecimiento ALTER COLUMN id_tipoestablecimiento SET DEFAULT nextval('public.tipoestablecimiento_id_tipoestablecimiento_seq'::regclass);
 Y   ALTER TABLE public.tipoestablecimiento ALTER COLUMN id_tipoestablecimiento DROP DEFAULT;
       public          admin    false    239    240    240                       2604    34984    tiposilla id_tiposilla    DEFAULT     �   ALTER TABLE ONLY public.tiposilla ALTER COLUMN id_tiposilla SET DEFAULT nextval('public.tiposilla_id_tiposilla_seq'::regclass);
 E   ALTER TABLE public.tiposilla ALTER COLUMN id_tiposilla DROP DEFAULT;
       public          admin    false    241    242    242            �          0    34864    boletabonus 
   TABLE DATA           U   COPY public.boletabonus (id_boletabonus, cliente_id, fecha, fechalimite) FROM stdin;
    public          admin    false    203   ��       �          0    34870    calificacion 
   TABLE DATA           Z   COPY public.calificacion (id_calificacion, puntuacion, pelicula_id, snack_id) FROM stdin;
    public          admin    false    205   '�       �          0    34876    cliente 
   TABLE DATA           �   COPY public.cliente (id_cliente, nombre, documento, tipodocumento, telefono, "contraseña", maxsillas, totalpuntos, usuario) FROM stdin;
    public          admin    false    207   D�       �          0    34882    compraboletas 
   TABLE DATA           h   COPY public.compraboletas (id_compraboletas, fecha, cantidadbonus, empleado_id, cliente_id) FROM stdin;
    public          admin    false    209   ��       �          0    34888    comprasnacks 
   TABLE DATA           W   COPY public.comprasnacks (id_comprasnacks, fecha, cliente_id, empleado_id) FROM stdin;
    public          admin    false    211   ��       �          0    34894    comprasnacks_snack 
   TABLE DATA           ^   COPY public.comprasnacks_snack (id_comprasnacks_snack, snack_id, comprasnacks_id) FROM stdin;
    public          admin    false    213   ��       �          0    34900    empleado 
   TABLE DATA           �   COPY public.empleado (id_empleado, codigo, nombre, cargo, documento, tipodocumento, telefono, "contraseña", establecimiento_id, fechainiciocontrato, salario, estado) FROM stdin;
    public          admin    false    215   �       �          0    34906    establecimiento 
   TABLE DATA              COPY public.establecimiento (id_establecimiento, nombre, ubicacion, max_empleados, tipoestablecimiento_id, estado) FROM stdin;
    public          admin    false    217   ��       �          0    34912    historialboletas 
   TABLE DATA           �   COPY public.historialboletas (id_historialboletas, compra_id, cantidadboletas, valortotal, cantidadpreferencial, cantidadgeneral, fechaventa, nombrepelicula, generopelicula, empleado_id) FROM stdin;
    public          admin    false    219   �       �          0    34920    historialcargos 
   TABLE DATA           �   COPY public.historialcargos (id_historialcargos, empleado_id, codigo, cargo, fechainiciocontrato, nombreestablecimiento, establecimiento_id, salario, fechafincontrato) FROM stdin;
    public          admin    false    222   9�       �          0    34927    historialsnacks 
   TABLE DATA           �   COPY public.historialsnacks (id_historialsnacks, comprasnack_id, totalsnacks, valortotal, establecimiento_id, empleado_id, fechaventa) FROM stdin;
    public          admin    false    224   V�       �          0    34933    inventariogeneral 
   TABLE DATA           k   COPY public.inventariogeneral (id_snack, nombre, stock, valorunidad, puntos, fechavencimiento) FROM stdin;
    public          admin    false    226   s�       �          0    34939    pelicula 
   TABLE DATA           s   COPY public.pelicula (id_pelicula, nombre, duracion, genero, fechainicio, horainicio, sala_id, estado) FROM stdin;
    public          admin    false    228   ��       �          0    34945    pelicula_silla 
   TABLE DATA           i   COPY public.pelicula_silla (id_pelicula_silla, pelicula_id, silla_id, compra_id, reserva_id) FROM stdin;
    public          admin    false    230   c�       �          0    34951    reservaspelicula 
   TABLE DATA           j   COPY public.reservaspelicula (id_reserva, cliente_id, fecha, estado, valortotal, puntostotal) FROM stdin;
    public          admin    false    232   ~       �          0    34957    sala 
   TABLE DATA           v   COPY public.sala (id_sala, cantidadgeneral, cantidadpreferencial, numerosala, establecimiento_id, estado) FROM stdin;
    public          admin    false    234   �       �          0    34963    silla 
   TABLE DATA           F   COPY public.silla (id_silla, fila, columna, tiposilla_id) FROM stdin;
    public          admin    false    236   �                  0    34969    snack 
   TABLE DATA           j   COPY public.snack (id_snacks, establecimiento_id, stockestablecimiento, inventariogeneral_id) FROM stdin;
    public          admin    false    238   r                0    34975    tipoestablecimiento 
   TABLE DATA           K   COPY public.tipoestablecimiento (id_tipoestablecimiento, tipo) FROM stdin;
    public          admin    false    240   �                0    34981 	   tiposilla 
   TABLE DATA           I   COPY public.tiposilla (id_tiposilla, nombre, precio, puntos) FROM stdin;
    public          admin    false    242   �                  0    0    boletabonus_id_boletabonus_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.boletabonus_id_boletabonus_seq', 1, true);
          public          admin    false    202            !           0    0     calificacion_id_calificacion_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.calificacion_id_calificacion_seq', 1, false);
          public          admin    false    204            "           0    0    cliente_id_cliente_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 2, true);
          public          admin    false    206            #           0    0 "   compraboletas_id_compraboletas_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.compraboletas_id_compraboletas_seq', 1, false);
          public          admin    false    208            $           0    0     comprasnacks_id_comprasnacks_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.comprasnacks_id_comprasnacks_seq', 1, false);
          public          admin    false    210            %           0    0 ,   comprasnacks_snack_id_comprasnacks_snack_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public.comprasnacks_snack_id_comprasnacks_snack_seq', 1, false);
          public          admin    false    212            &           0    0    empleado_id_empleado_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.empleado_id_empleado_seq', 3, true);
          public          admin    false    214            '           0    0 &   establecimiento_id_establecimiento_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.establecimiento_id_establecimiento_seq', 2, true);
          public          admin    false    216            (           0    0 (   historialboletas_id_historialboletas_seq    SEQUENCE SET     W   SELECT pg_catalog.setval('public.historialboletas_id_historialboletas_seq', 1, false);
          public          admin    false    218            )           0    0    historialcargos_empleado_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.historialcargos_empleado_id_seq', 1, false);
          public          admin    false    221            *           0    0 &   historialcargos_id_historialcargos_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.historialcargos_id_historialcargos_seq', 1, false);
          public          admin    false    220            +           0    0 &   historialsnacks_id_historialsnacks_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.historialsnacks_id_historialsnacks_seq', 1, false);
          public          admin    false    223            ,           0    0    inventariogeneral_id_snack_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.inventariogeneral_id_snack_seq', 2, true);
          public          admin    false    225            -           0    0    pelicula_id_pelicula_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.pelicula_id_pelicula_seq', 7, true);
          public          admin    false    227            .           0    0 $   pelicula_silla_id_pelicula_silla_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.pelicula_silla_id_pelicula_silla_seq', 420, true);
          public          admin    false    229            /           0    0    reservaspelicula_id_reserva_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.reservaspelicula_id_reserva_seq', 1, false);
          public          admin    false    231            0           0    0    sala_id_sala_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.sala_id_sala_seq', 10, true);
          public          admin    false    233            1           0    0    silla_id_silla_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.silla_id_silla_seq', 420, true);
          public          admin    false    235            2           0    0    snack_id_snacks_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.snack_id_snacks_seq', 4, true);
          public          admin    false    237            3           0    0 .   tipoestablecimiento_id_tipoestablecimiento_seq    SEQUENCE SET     \   SELECT pg_catalog.setval('public.tipoestablecimiento_id_tipoestablecimiento_seq', 3, true);
          public          admin    false    239            4           0    0    tiposilla_id_tiposilla_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.tiposilla_id_tiposilla_seq', 2, true);
          public          admin    false    241            (           2606    35017    historialboletas pk_auditoria1 
   CONSTRAINT     m   ALTER TABLE ONLY public.historialboletas
    ADD CONSTRAINT pk_auditoria1 PRIMARY KEY (id_historialboletas);
 H   ALTER TABLE ONLY public.historialboletas DROP CONSTRAINT pk_auditoria1;
       public            admin    false    219            ,           2606    35021    historialsnacks pk_auditoria2 
   CONSTRAINT     k   ALTER TABLE ONLY public.historialsnacks
    ADD CONSTRAINT pk_auditoria2 PRIMARY KEY (id_historialsnacks);
 G   ALTER TABLE ONLY public.historialsnacks DROP CONSTRAINT pk_auditoria2;
       public            admin    false    224            
           2606    34986    boletabonus pk_boletabonus 
   CONSTRAINT     d   ALTER TABLE ONLY public.boletabonus
    ADD CONSTRAINT pk_boletabonus PRIMARY KEY (id_boletabonus);
 D   ALTER TABLE ONLY public.boletabonus DROP CONSTRAINT pk_boletabonus;
       public            admin    false    203                       2606    34989    calificacion pk_calificacion 
   CONSTRAINT     g   ALTER TABLE ONLY public.calificacion
    ADD CONSTRAINT pk_calificacion PRIMARY KEY (id_calificacion);
 F   ALTER TABLE ONLY public.calificacion DROP CONSTRAINT pk_calificacion;
       public            admin    false    205                       2606    34993    cliente pk_cliente 
   CONSTRAINT     X   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id_cliente);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cliente;
       public            admin    false    207                       2606    34997    compraboletas pk_compraboletas 
   CONSTRAINT     j   ALTER TABLE ONLY public.compraboletas
    ADD CONSTRAINT pk_compraboletas PRIMARY KEY (id_compraboletas);
 H   ALTER TABLE ONLY public.compraboletas DROP CONSTRAINT pk_compraboletas;
       public            admin    false    209                       2606    35001    comprasnacks pk_comprasnacks 
   CONSTRAINT     g   ALTER TABLE ONLY public.comprasnacks
    ADD CONSTRAINT pk_comprasnacks PRIMARY KEY (id_comprasnacks);
 F   ALTER TABLE ONLY public.comprasnacks DROP CONSTRAINT pk_comprasnacks;
       public            admin    false    211                       2606    35005 )   comprasnacks_snack pk_comprasnacks_snacks 
   CONSTRAINT     z   ALTER TABLE ONLY public.comprasnacks_snack
    ADD CONSTRAINT pk_comprasnacks_snacks PRIMARY KEY (id_comprasnacks_snack);
 S   ALTER TABLE ONLY public.comprasnacks_snack DROP CONSTRAINT pk_comprasnacks_snacks;
       public            admin    false    213            !           2606    35009    empleado pk_empleado 
   CONSTRAINT     [   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT pk_empleado PRIMARY KEY (id_empleado);
 >   ALTER TABLE ONLY public.empleado DROP CONSTRAINT pk_empleado;
       public            admin    false    215            &           2606    35014 "   establecimiento pk_establecimiento 
   CONSTRAINT     p   ALTER TABLE ONLY public.establecimiento
    ADD CONSTRAINT pk_establecimiento PRIMARY KEY (id_establecimiento);
 L   ALTER TABLE ONLY public.establecimiento DROP CONSTRAINT pk_establecimiento;
       public            admin    false    217            *           2606    35019 "   historialcargos pk_historialcargos 
   CONSTRAINT     p   ALTER TABLE ONLY public.historialcargos
    ADD CONSTRAINT pk_historialcargos PRIMARY KEY (id_historialcargos);
 L   ALTER TABLE ONLY public.historialcargos DROP CONSTRAINT pk_historialcargos;
       public            admin    false    222            .           2606    35023 &   inventariogeneral pk_inventariogeneral 
   CONSTRAINT     j   ALTER TABLE ONLY public.inventariogeneral
    ADD CONSTRAINT pk_inventariogeneral PRIMARY KEY (id_snack);
 P   ALTER TABLE ONLY public.inventariogeneral DROP CONSTRAINT pk_inventariogeneral;
       public            admin    false    226            1           2606    35025    pelicula pk_pelicula 
   CONSTRAINT     [   ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT pk_pelicula PRIMARY KEY (id_pelicula);
 >   ALTER TABLE ONLY public.pelicula DROP CONSTRAINT pk_pelicula;
       public            admin    false    228            7           2606    35028     pelicula_silla pk_pelicula_silla 
   CONSTRAINT     m   ALTER TABLE ONLY public.pelicula_silla
    ADD CONSTRAINT pk_pelicula_silla PRIMARY KEY (id_pelicula_silla);
 J   ALTER TABLE ONLY public.pelicula_silla DROP CONSTRAINT pk_pelicula_silla;
       public            admin    false    230            :           2606    35034 $   reservaspelicula pk_reservaspelicula 
   CONSTRAINT     j   ALTER TABLE ONLY public.reservaspelicula
    ADD CONSTRAINT pk_reservaspelicula PRIMARY KEY (id_reserva);
 N   ALTER TABLE ONLY public.reservaspelicula DROP CONSTRAINT pk_reservaspelicula;
       public            admin    false    232            =           2606    35037    sala pk_sala 
   CONSTRAINT     O   ALTER TABLE ONLY public.sala
    ADD CONSTRAINT pk_sala PRIMARY KEY (id_sala);
 6   ALTER TABLE ONLY public.sala DROP CONSTRAINT pk_sala;
       public            admin    false    234            @           2606    35040    silla pk_silla 
   CONSTRAINT     R   ALTER TABLE ONLY public.silla
    ADD CONSTRAINT pk_silla PRIMARY KEY (id_silla);
 8   ALTER TABLE ONLY public.silla DROP CONSTRAINT pk_silla;
       public            admin    false    236            D           2606    35043    snack pk_snacks 
   CONSTRAINT     T   ALTER TABLE ONLY public.snack
    ADD CONSTRAINT pk_snacks PRIMARY KEY (id_snacks);
 9   ALTER TABLE ONLY public.snack DROP CONSTRAINT pk_snacks;
       public            admin    false    238            F           2606    35047 *   tipoestablecimiento pk_tipoestablecimiento 
   CONSTRAINT     |   ALTER TABLE ONLY public.tipoestablecimiento
    ADD CONSTRAINT pk_tipoestablecimiento PRIMARY KEY (id_tipoestablecimiento);
 T   ALTER TABLE ONLY public.tipoestablecimiento DROP CONSTRAINT pk_tipoestablecimiento;
       public            admin    false    240            H           2606    35049    tiposilla pk_tiposilla 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tiposilla
    ADD CONSTRAINT pk_tiposilla PRIMARY KEY (id_tiposilla);
 @   ALTER TABLE ONLY public.tiposilla DROP CONSTRAINT pk_tiposilla;
       public            admin    false    242                       2606    34995    cliente uk_cliente_usuario 
   CONSTRAINT     X   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uk_cliente_usuario UNIQUE (usuario);
 D   ALTER TABLE ONLY public.cliente DROP CONSTRAINT uk_cliente_usuario;
       public            admin    false    207            #           2606    35011    empleado uk_empleado_codigo 
   CONSTRAINT     X   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT uk_empleado_codigo UNIQUE (codigo);
 E   ALTER TABLE ONLY public.empleado DROP CONSTRAINT uk_empleado_codigo;
       public            admin    false    215                       1259    34987    ixfk_boletabonus_cliente    INDEX     V   CREATE INDEX ixfk_boletabonus_cliente ON public.boletabonus USING btree (cliente_id);
 ,   DROP INDEX public.ixfk_boletabonus_cliente;
       public            admin    false    203                       1259    34990    ixfk_calificacion_pelicula    INDEX     Z   CREATE INDEX ixfk_calificacion_pelicula ON public.calificacion USING btree (pelicula_id);
 .   DROP INDEX public.ixfk_calificacion_pelicula;
       public            admin    false    205                       1259    34991    ixfk_calificacion_snack    INDEX     T   CREATE INDEX ixfk_calificacion_snack ON public.calificacion USING btree (snack_id);
 +   DROP INDEX public.ixfk_calificacion_snack;
       public            admin    false    205                       1259    34998    ixfk_compraboletas_cliente    INDEX     Z   CREATE INDEX ixfk_compraboletas_cliente ON public.compraboletas USING btree (cliente_id);
 .   DROP INDEX public.ixfk_compraboletas_cliente;
       public            admin    false    209                       1259    34999    ixfk_compraboletas_empleado    INDEX     \   CREATE INDEX ixfk_compraboletas_empleado ON public.compraboletas USING btree (empleado_id);
 /   DROP INDEX public.ixfk_compraboletas_empleado;
       public            admin    false    209                       1259    35002    ixfk_comprasnacks_cliente    INDEX     X   CREATE INDEX ixfk_comprasnacks_cliente ON public.comprasnacks USING btree (cliente_id);
 -   DROP INDEX public.ixfk_comprasnacks_cliente;
       public            admin    false    211                       1259    35003    ixfk_comprasnacks_empleado    INDEX     Z   CREATE INDEX ixfk_comprasnacks_empleado ON public.comprasnacks USING btree (empleado_id);
 .   DROP INDEX public.ixfk_comprasnacks_empleado;
       public            admin    false    211                       1259    35006 $   ixfk_comprasnacks_snack_comprasnacks    INDEX     n   CREATE INDEX ixfk_comprasnacks_snack_comprasnacks ON public.comprasnacks_snack USING btree (comprasnacks_id);
 8   DROP INDEX public.ixfk_comprasnacks_snack_comprasnacks;
       public            admin    false    213                       1259    35007    ixfk_comprasnacks_snack_snack    INDEX     `   CREATE INDEX ixfk_comprasnacks_snack_snack ON public.comprasnacks_snack USING btree (snack_id);
 1   DROP INDEX public.ixfk_comprasnacks_snack_snack;
       public            admin    false    213                       1259    35012    ixfk_empleado_establecimiento    INDEX     `   CREATE INDEX ixfk_empleado_establecimiento ON public.empleado USING btree (establecimiento_id);
 1   DROP INDEX public.ixfk_empleado_establecimiento;
       public            admin    false    215            $           1259    35015 (   ixfk_establecimiento_tipoestablecimiento    INDEX     v   CREATE INDEX ixfk_establecimiento_tipoestablecimiento ON public.establecimiento USING btree (tipoestablecimiento_id);
 <   DROP INDEX public.ixfk_establecimiento_tipoestablecimiento;
       public            admin    false    217            /           1259    35026    ixfk_pelicula_sala    INDEX     J   CREATE INDEX ixfk_pelicula_sala ON public.pelicula USING btree (sala_id);
 &   DROP INDEX public.ixfk_pelicula_sala;
       public            admin    false    228            2           1259    35029 !   ixfk_pelicula_silla_compraboletas    INDEX     a   CREATE INDEX ixfk_pelicula_silla_compraboletas ON public.pelicula_silla USING btree (compra_id);
 5   DROP INDEX public.ixfk_pelicula_silla_compraboletas;
       public            admin    false    230            3           1259    35030    ixfk_pelicula_silla_pelicula    INDEX     ^   CREATE INDEX ixfk_pelicula_silla_pelicula ON public.pelicula_silla USING btree (pelicula_id);
 0   DROP INDEX public.ixfk_pelicula_silla_pelicula;
       public            admin    false    230            4           1259    35031 $   ixfk_pelicula_silla_reservaspelicula    INDEX     e   CREATE INDEX ixfk_pelicula_silla_reservaspelicula ON public.pelicula_silla USING btree (reserva_id);
 8   DROP INDEX public.ixfk_pelicula_silla_reservaspelicula;
       public            admin    false    230            5           1259    35032    ixfk_pelicula_silla_silla    INDEX     X   CREATE INDEX ixfk_pelicula_silla_silla ON public.pelicula_silla USING btree (silla_id);
 -   DROP INDEX public.ixfk_pelicula_silla_silla;
       public            admin    false    230            8           1259    35035    ixfk_reservaspelicula_cliente    INDEX     `   CREATE INDEX ixfk_reservaspelicula_cliente ON public.reservaspelicula USING btree (cliente_id);
 1   DROP INDEX public.ixfk_reservaspelicula_cliente;
       public            admin    false    232            ;           1259    35038    ixfk_sala_establecimiento    INDEX     X   CREATE INDEX ixfk_sala_establecimiento ON public.sala USING btree (establecimiento_id);
 -   DROP INDEX public.ixfk_sala_establecimiento;
       public            admin    false    234            >           1259    35041    ixfk_silla_tiposilla    INDEX     N   CREATE INDEX ixfk_silla_tiposilla ON public.silla USING btree (tiposilla_id);
 (   DROP INDEX public.ixfk_silla_tiposilla;
       public            admin    false    236            A           1259    35044    ixfk_snacks_establecimiento    INDEX     [   CREATE INDEX ixfk_snacks_establecimiento ON public.snack USING btree (establecimiento_id);
 /   DROP INDEX public.ixfk_snacks_establecimiento;
       public            admin    false    238            B           1259    35045    ixfk_snacks_inventariogeneral    INDEX     _   CREATE INDEX ixfk_snacks_inventariogeneral ON public.snack USING btree (inventariogeneral_id);
 1   DROP INDEX public.ixfk_snacks_inventariogeneral;
       public            admin    false    238            I           2606    35050 "   boletabonus fk_boletabonus_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.boletabonus
    ADD CONSTRAINT fk_boletabonus_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(id_cliente);
 L   ALTER TABLE ONLY public.boletabonus DROP CONSTRAINT fk_boletabonus_cliente;
       public          admin    false    207    203    2832            J           2606    35055 %   calificacion fk_calificacion_pelicula    FK CONSTRAINT     �   ALTER TABLE ONLY public.calificacion
    ADD CONSTRAINT fk_calificacion_pelicula FOREIGN KEY (pelicula_id) REFERENCES public.pelicula(id_pelicula);
 O   ALTER TABLE ONLY public.calificacion DROP CONSTRAINT fk_calificacion_pelicula;
       public          admin    false    2865    228    205            K           2606    35060 "   calificacion fk_calificacion_snack    FK CONSTRAINT     �   ALTER TABLE ONLY public.calificacion
    ADD CONSTRAINT fk_calificacion_snack FOREIGN KEY (snack_id) REFERENCES public.snack(id_snacks);
 L   ALTER TABLE ONLY public.calificacion DROP CONSTRAINT fk_calificacion_snack;
       public          admin    false    205    2884    238            L           2606    35065 &   compraboletas fk_compraboletas_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.compraboletas
    ADD CONSTRAINT fk_compraboletas_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(id_cliente);
 P   ALTER TABLE ONLY public.compraboletas DROP CONSTRAINT fk_compraboletas_cliente;
       public          admin    false    2832    209    207            M           2606    35070 '   compraboletas fk_compraboletas_empleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.compraboletas
    ADD CONSTRAINT fk_compraboletas_empleado FOREIGN KEY (empleado_id) REFERENCES public.empleado(id_empleado);
 Q   ALTER TABLE ONLY public.compraboletas DROP CONSTRAINT fk_compraboletas_empleado;
       public          admin    false    2849    215    209            N           2606    35075 $   comprasnacks fk_comprasnacks_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.comprasnacks
    ADD CONSTRAINT fk_comprasnacks_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(id_cliente);
 N   ALTER TABLE ONLY public.comprasnacks DROP CONSTRAINT fk_comprasnacks_cliente;
       public          admin    false    207    2832    211            O           2606    35080 %   comprasnacks fk_comprasnacks_empleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.comprasnacks
    ADD CONSTRAINT fk_comprasnacks_empleado FOREIGN KEY (empleado_id) REFERENCES public.empleado(id_empleado);
 O   ALTER TABLE ONLY public.comprasnacks DROP CONSTRAINT fk_comprasnacks_empleado;
       public          admin    false    2849    211    215            P           2606    35085 5   comprasnacks_snack fk_comprasnacks_snack_comprasnacks    FK CONSTRAINT     �   ALTER TABLE ONLY public.comprasnacks_snack
    ADD CONSTRAINT fk_comprasnacks_snack_comprasnacks FOREIGN KEY (comprasnacks_id) REFERENCES public.comprasnacks(id_comprasnacks);
 _   ALTER TABLE ONLY public.comprasnacks_snack DROP CONSTRAINT fk_comprasnacks_snack_comprasnacks;
       public          admin    false    211    2842    213            Q           2606    35090 .   comprasnacks_snack fk_comprasnacks_snack_snack    FK CONSTRAINT     �   ALTER TABLE ONLY public.comprasnacks_snack
    ADD CONSTRAINT fk_comprasnacks_snack_snack FOREIGN KEY (snack_id) REFERENCES public.snack(id_snacks);
 X   ALTER TABLE ONLY public.comprasnacks_snack DROP CONSTRAINT fk_comprasnacks_snack_snack;
       public          admin    false    213    238    2884            R           2606    35095 $   empleado fk_empleado_establecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_empleado_establecimiento FOREIGN KEY (establecimiento_id) REFERENCES public.establecimiento(id_establecimiento);
 N   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_empleado_establecimiento;
       public          admin    false    215    217    2854            S           2606    35100 6   establecimiento fk_establecimiento_tipoestablecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.establecimiento
    ADD CONSTRAINT fk_establecimiento_tipoestablecimiento FOREIGN KEY (tipoestablecimiento_id) REFERENCES public.tipoestablecimiento(id_tipoestablecimiento);
 `   ALTER TABLE ONLY public.establecimiento DROP CONSTRAINT fk_establecimiento_tipoestablecimiento;
       public          admin    false    2886    240    217            T           2606    35105    pelicula fk_pelicula_sala    FK CONSTRAINT     |   ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT fk_pelicula_sala FOREIGN KEY (sala_id) REFERENCES public.sala(id_sala);
 C   ALTER TABLE ONLY public.pelicula DROP CONSTRAINT fk_pelicula_sala;
       public          admin    false    234    2877    228            U           2606    35110 .   pelicula_silla fk_pelicula_silla_compraboletas    FK CONSTRAINT     �   ALTER TABLE ONLY public.pelicula_silla
    ADD CONSTRAINT fk_pelicula_silla_compraboletas FOREIGN KEY (compra_id) REFERENCES public.compraboletas(id_compraboletas);
 X   ALTER TABLE ONLY public.pelicula_silla DROP CONSTRAINT fk_pelicula_silla_compraboletas;
       public          admin    false    209    230    2838            V           2606    35115 )   pelicula_silla fk_pelicula_silla_pelicula    FK CONSTRAINT     �   ALTER TABLE ONLY public.pelicula_silla
    ADD CONSTRAINT fk_pelicula_silla_pelicula FOREIGN KEY (pelicula_id) REFERENCES public.pelicula(id_pelicula);
 S   ALTER TABLE ONLY public.pelicula_silla DROP CONSTRAINT fk_pelicula_silla_pelicula;
       public          admin    false    230    228    2865            W           2606    35120 1   pelicula_silla fk_pelicula_silla_reservaspelicula    FK CONSTRAINT     �   ALTER TABLE ONLY public.pelicula_silla
    ADD CONSTRAINT fk_pelicula_silla_reservaspelicula FOREIGN KEY (reserva_id) REFERENCES public.reservaspelicula(id_reserva);
 [   ALTER TABLE ONLY public.pelicula_silla DROP CONSTRAINT fk_pelicula_silla_reservaspelicula;
       public          admin    false    230    2874    232            X           2606    35125 &   pelicula_silla fk_pelicula_silla_silla    FK CONSTRAINT     �   ALTER TABLE ONLY public.pelicula_silla
    ADD CONSTRAINT fk_pelicula_silla_silla FOREIGN KEY (silla_id) REFERENCES public.silla(id_silla);
 P   ALTER TABLE ONLY public.pelicula_silla DROP CONSTRAINT fk_pelicula_silla_silla;
       public          admin    false    230    2880    236            Y           2606    35130 ,   reservaspelicula fk_reservaspelicula_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservaspelicula
    ADD CONSTRAINT fk_reservaspelicula_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(id_cliente);
 V   ALTER TABLE ONLY public.reservaspelicula DROP CONSTRAINT fk_reservaspelicula_cliente;
       public          admin    false    2832    232    207            Z           2606    35135    sala fk_sala_establecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.sala
    ADD CONSTRAINT fk_sala_establecimiento FOREIGN KEY (establecimiento_id) REFERENCES public.establecimiento(id_establecimiento);
 F   ALTER TABLE ONLY public.sala DROP CONSTRAINT fk_sala_establecimiento;
       public          admin    false    234    217    2854            [           2606    35140    silla fk_silla_tiposilla    FK CONSTRAINT     �   ALTER TABLE ONLY public.silla
    ADD CONSTRAINT fk_silla_tiposilla FOREIGN KEY (tiposilla_id) REFERENCES public.tiposilla(id_tiposilla);
 B   ALTER TABLE ONLY public.silla DROP CONSTRAINT fk_silla_tiposilla;
       public          admin    false    242    236    2888            \           2606    35145    snack fk_snacks_establecimiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.snack
    ADD CONSTRAINT fk_snacks_establecimiento FOREIGN KEY (establecimiento_id) REFERENCES public.establecimiento(id_establecimiento);
 I   ALTER TABLE ONLY public.snack DROP CONSTRAINT fk_snacks_establecimiento;
       public          admin    false    238    2854    217            ]           2606    35150 !   snack fk_snacks_inventariogeneral    FK CONSTRAINT     �   ALTER TABLE ONLY public.snack
    ADD CONSTRAINT fk_snacks_inventariogeneral FOREIGN KEY (inventariogeneral_id) REFERENCES public.inventariogeneral(id_snack);
 K   ALTER TABLE ONLY public.snack DROP CONSTRAINT fk_snacks_inventariogeneral;
       public          admin    false    2862    226    238            �       x�3�4�4202�5��521!L�=... Rd      �      x������ � �      �   j   x�3��/NN,R�H-�K�KI��4402212700�tv�462064� NCNSN��`� C.#N�ĒĜ�DǼ�Ģ̒DNC#c2�Ѧf���Fp}F\1z\\\ �      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x�]̻
�@�z�+��ά���U�B���BBB��}�DI�)�q��*gM�.-�o���LQ��X j\��f�a;	1��hʱV��������Tܧ�#:?�ew�'k'kI34��HWʺ���k�w����&�9�5���C��z�<      �   E   x�3�tvV�u���u�tvt�<<�O.fh�i������eR�����珦"��ih�P���� �:      �      x������ � �      �      x������ � �      �      x������ � �      �   P   x�3�ppV�urw�qT050p�440�4200�҆@��������1�g�k@��������D�)�:��=... x��      �   �   x��˻�0���
7`F�����Q`�]Q�r	�6_?����A�"���p.9����/Ύv]��m��;o|b��'Y8]=�Ti ��y�B���ޗ�K�oF�oB��p�q���'��c�=�^�      �     x�M�ۍ�7E��L1�ϋnM��_Gč�#��{�KG������w���=��9�9~�y��{�<~����y���=�{޿�s���l_}��S}��g[}���[}���[�Vk��ZaŰvXA�%Vk�����eq���/�����m�x[�,�/�����m�x[�,і(K�%���蟥-Q�hK�%�e��DY�-Q�hK�%ڒeɶdY�-Y�lK�%��I[�,ٖ,K�%˒mɲd[�,ٖQ�іQ�іQ�іQ�іQ��O�~d�2�2�2�2�2�2�2�2�2�2�2�e�ezMm�QS[f�Ԗ9j��g�Ԗ�jj��5�e��ڲ�;������������������������,~�˲ڲʲڲʲڲ˲۲˲۲˲۲˲۲˲۲˲۲˲۲˲�-e�m�e�m9e9m9e9m9e9m9e9m9e9m9e9m9e9m9e9m9e9l�����
S���4��>����B3;�K�l�ohf�}S3��[��}������f|&�_�X���M���M���M���M���M���M���M���M>���|lh����k�w7t�����5�{�f|wS׌�����m]3�����ֶ���m!_������m!��B>6��|�p�����-�c�[��.���mn)_����F����n)[�R>���|lvK���6�c�ې��nC>6����6��V {ކ|lz��m�Ƕ�!�ަ|l|���m��ַ){ߦ|l~���wo���oS>��M�(�M�h�-���-��-�(�-�h�-���-��-�ֻY�Gl�Gl�Gl�Gl��h��.Ԍf|�5�u���C��n!j~w��߭D��n'�L-��G/��G1��G3��G5��G7��G9��G;��w��T����s��i���fn�_h���fn����]�����~K3��ok�����L>{7h�臛|��M>��&�p��~��G?��n��7�����{7|��;�|���{���n�.߻�|�������n�.��~x�G?<��-D>��!��~x�G?<����臧|�ó��9��[����r���'�f|�5���-G��n9j�w�Q3�[�;���������MN>��C>��C>��C>��C>��S>��S>��S>��S>��S>��S���k�G?|�G?|�G?|�G?|�G?|�G?|�G?|�G?|�G?|�G?|ɷ޷a��/��/��o��o��o��o��o��o��o��o����.��-��#��#��#��#��#��#��#��#��#�y/
���P����[MЏ�娙��[��y[�娙ׅ[��y_�娙�[��yc�娙W�[����r��ޫ�|�#L>�&���~��G?��a�я0��G�|�#\>�.��w��G�|�#\>�.���~��G?����7����|��(����H��v�ף���|�)�{oH!�{E
��G�|�#R>�)���/�ۖ|�#R>�)����~D�G?"�1�1�1�1�1���M>�C>�C>�C>�C>�S>���W�~�-G��n9j�w�Q3�[�����Ԍf|�5���-ǝ�G,��G,��G,��G,��G,��G,��G,��G,��G,��Gl��Gl��Gl��Gl��Gl��Gl��Gl��Gl��Gl��Gl��G��G��G��G��G��G��G��G��G��G��G~�K���i�-�sͼ����/5�"�ͼ	S3����̻�5�2���L>��&�H��~��G?��i�я4��G�|�#M>��&�H��~����������      �      x������ � �      �   T   x�3�41�42�4B�P?gO?G.#����1T�E�*j�"j
5E5��f�$j�Y�n��%�6dQC�u��1z\\\ ��*�      �   c  x�M�K�\7D�q�bf�C[�V����ɶ�R#�(!�n���O|�+��zU�����zG���Y�wջ����zO���h�P�+>}���qo�jܣ�kܫ1jܳ1kܻ�j�ñk��qj�����{:���mָ�S5���5��5��5��\5���5��<5�i�{�V|��>����?���z�{Z��=�Y�֪qOk׸�uj�ӽ݃�t��?u:�z��;TCw���Qc�1k�;V�uǮ��85N����vW|~~�Y��qO�^���==f�{z����5��qj�ӳ݃���eLӘ�1�c��<�}L���,dYȲ�e!�B��,Y�,d#d[ȶ�m!�B��l��-d[�Aȱ�c!�B��9r,�Xȱ�h�f#ь$��D3�hv�P�YJ4S�f+`���s���������������H�D�M��DZN��D�N��DZO��D���#~c�~d��?3�wF�Ј_�S#K���ݘ�[Sts�nO�*�EE7��6T1�*�YŰ��òb�Vۊa\1�5���������������������t-t-t-t-t-t-t-t-t-t��k�k�k�k�k�k�k�k�k��<]]]]]]]]�}�ޗ�}�߲�Ǭ�5k|�߳���Ek|Қue�+ú2�+ú2�+ú2�+ú2�+ú2ѕi]�֕i]�֕i]�֕i]�֕i])t��+e])�JYWʺR֕���u��+;��[Wv��n]٭+�ue���֕ݺ�[Wt尮֕úrXW��a]9�+�u�@�|�&�&�&�&��%tMtMtMt��k�k�k�k�k�k�k�k�k�k?]]]]]]]]]]��:�:�:�:�:�:�:�:�:֥�.5�R���u�Y��u�Y��u�Y��u�ŷ�o�ߢ�E��	.\D��p�.B\��Hq��"�E�����߂��oIN��(U���u�s�\/�E��8u.�\��tQ�"�E��E��t���E��R�.Z]ĺ�u�\�.�]�Hv��"�E��lݮ��E��v�.�]��w���^4��xQ�"�Eǋ�%/R^��^̋�9/z^�(z����uޟ}����Uߩ�N�w��S����T}��;U�_�w��S����T}��;Uߩ�N�w���������׿��Dw          %   x�3�4�42�4�2�0���9� "& �)P$F��� l�          2   x�3�t��K�M��2�(�+�WpL?�6�˘ӱ '39�$�,�+F��� N�         3   x�3�tw�sr��44400�30�44�2�rusr�s�I�"�b���� 3>
�     