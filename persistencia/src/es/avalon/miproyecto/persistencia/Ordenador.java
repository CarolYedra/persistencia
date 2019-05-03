package es.avalon.miproyecto.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ordenador {

	private String modelo;
	private String marca;
	private double precio;

	public Ordenador(String modelo, String marca, double precio) {
		super();
		this.modelo = modelo;
		this.marca = marca;
		this.precio = precio;
	}

	public Ordenador(String modelo) {
		super();
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public static List<Ordenador> buscarTodos() {
		List<Ordenador> ordenadores = new ArrayList<Ordenador>();
		ResultSet rs = null;
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			String sql = "select * from Ordenador1";
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				String modelo = rs.getString("modelo");
				String marca = rs.getString("marca");
				double importe = rs.getDouble("precio");
				Ordenador o = new Ordenador(modelo, marca, importe);
				ordenadores.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenadores;

	}

//INSERTAR
	public void insertar() {
		String sql = "insert into ordenador1 (modelo, marca, precio) values (?,?,?)";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {
			sentencia.setString(1, modelo);
			sentencia.setString(2, marca);
			sentencia.setDouble(3, precio);
			sentencia.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//BORRAR
	public static void borrar(String modelo) throws ClassNotFoundException {
		String sql = "DELETE FROM Ordenador1 where modelo = ?";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.borrarPreparedStatement(conexion, sql);) {
			sentencia.setString(1, modelo);
			sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//ACTUALIZAR
	public void update() throws ClassNotFoundException {
		System.out.println(this.getMarca()+" "+this.getModelo()+" "+this.getPrecio());
		String sql = "update Ordenador1 set marca ='" + this.getMarca() 
				+ "', precio=" + this.getPrecio()
				+ " where modelo ='" + this.getModelo() + "'";
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			sentencia.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ACTUALIZADO!");
		}
	}

//BUSCAR UNO
	public static Ordenador buscarUno(String modelo) throws ClassNotFoundException {
		Ordenador o = null;
		String sql = "select * FROM Ordenador1  WHERE modelo ='" + modelo + "'";
		try (Connection conexion = DBHelper.crearConexion();
				Statement sentencia = DBHelper.crearStatement(conexion);
				ResultSet rs = sentencia.executeQuery(sql)) {
			rs.next();
			o = new Ordenador(rs.getString("modelo"), rs.getString("marca"), rs.getDouble("precio"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

//BUSCAR por modelo

		public static List<Ordenador> buscarTodosModelo(String modeloOrdenador) {
			List<Ordenador> ordenadores = new ArrayList<Ordenador>();
			ResultSet rs = null;
			try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
				String sql = "select * from Ordenador1 where modelo='"+modeloOrdenador+"'";
				rs = sentencia.executeQuery(sql);
				while (rs.next()) {
					String modelo = rs.getString("modelo");
					String marca = rs.getString("marca");
					double importe = rs.getDouble("precio");
					Ordenador o = new Ordenador(modelo, marca, importe);
					ordenadores.add(o);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ordenadores;
	}
//filtrar

				public static List<Ordenador> filtrar(String filtro) {
					List<Ordenador> ordenadores = new ArrayList<Ordenador>();
					ResultSet rs = null;
					try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
						String sql = "select * from Ordenador1 order by "+filtro;
						rs = sentencia.executeQuery(sql);
						while (rs.next()) {
							String modelo = rs.getString("modelo");
							String marca = rs.getString("marca");
							double importe = rs.getDouble("precio");
							Ordenador o = new Ordenador(modelo, marca, importe);
							ordenadores.add(o);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ordenadores;	
}}