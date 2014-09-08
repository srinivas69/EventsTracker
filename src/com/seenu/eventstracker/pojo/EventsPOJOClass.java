package com.seenu.eventstracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class EventsPOJOClass implements Parcelable {

	public String id;
	public String event;
	public String location;
	public String eventType;
	public String date;
	public String time;
	public String url;

	public EventsPOJOClass(String id, String event, String location,
			String eventType, String date, String time, String url) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.event = event;
		this.location = location;
		this.eventType = eventType;
		this.date = date;
		this.time = time;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stubneedDateParsed

		dest.writeString(id);
		dest.writeString(event);
		dest.writeString(location);
		dest.writeString(eventType);
		dest.writeString(date);
		dest.writeString(time);
		dest.writeString(url);
	}

	private EventsPOJOClass(Parcel in) {

		// this();
		this.id = in.readString();
		this.event = in.readString();
		this.location = in.readString();
		this.eventType = in.readString();
		this.date = in.readString();
		this.time = in.readString();
		this.url = in.readString();

	}

	public static final Parcelable.Creator<EventsPOJOClass> CREATOR = new Parcelable.Creator<EventsPOJOClass>() {

		@Override
		public EventsPOJOClass createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new EventsPOJOClass(source);
		}

		@Override
		public EventsPOJOClass[] newArray(int size) {
			// TODO Auto-generated method stub
			return new EventsPOJOClass[size];
		}
	};

}
