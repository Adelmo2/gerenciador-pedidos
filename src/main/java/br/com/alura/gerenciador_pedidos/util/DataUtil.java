package br.com.alura.gerenciador_pedidos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataUtil {

	public static String getDataHora() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return localDateTime.format(formatter);
	}

	public static String getDataHora(Date data) {
    	LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    	return localDateTime.format(formatter);
    }
    
    public static String getDataHora(Date data, String hora) {
    	LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'" + hora);
    	return localDateTime.format(formatter);
    }

	public static String getDataHora(long dias) {
		LocalDateTime localDateTime = LocalDateTime.now().plusDays(dias);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return localDateTime.format(formatter);
	}

    public static Date getDataHoraAtual() {
    	LocalDateTime localDateTime = LocalDateTime.now();
    	return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

	public static Date getData(String dataHora) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dataHora, formatter);
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String getData(Date data, String pattern) {
		if (data == null) {
			return "null";
		}
		LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	public static String getSequenciaDataHoraAtual() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		return localDateTime.format(formatter);
	}

	public static Calendar getCalendar(String data) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
			calendar.setTime(sdf.parse(data));
			return calendar;
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Date getDataAdicionandoMes(int meses) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, meses);
		return calendar.getTime();
	}

	public static boolean isDataMaiorQueData(Date data1, Date data2) {
		return data1.after(data2);
	}

	public static boolean isDataIgualAData(Date data1, Date data2) {
		return data1.equals(data2);
	}

	public static Date getDataSemHora(Date data) {
		LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
