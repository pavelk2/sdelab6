package introsde.rest.ehealth.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "healthprofile")
@XmlType(propOrder = { "weight", "height", "BMI","history" })
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {
	private double weight; // in kg
	private double height; // in m
	private String history; // date of the last update

	// @XmlAttribute(name="id")
	// private Long healthprofileId;

	public HealthProfile(double weight, double height) {
		this.weight = weight;
		this.height = height;
		this.history = getNow();
	}

	public String getNow() {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();
		return df.format(today);
	}

	public HealthProfile() {
		this.weight = 85.5;
		this.height = 1.72;
		this.history = getNow();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}
	public String getHistory() {
		return history;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@XmlElement(name = "bmi")
	public double getBMI() {
		return this.weight / (Math.pow(this.height, 2));
	}

	public String toString() {
		return "{" + this.height + "," + this.weight + "," + this.getBMI()
				+ "," + "}";
	}

}
