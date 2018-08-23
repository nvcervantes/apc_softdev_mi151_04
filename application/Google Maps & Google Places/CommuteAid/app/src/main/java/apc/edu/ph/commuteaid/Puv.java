package apc.edu.ph.commuteaid;



public class Puv {




    public String id, platenumber, category, date, start, end, availableseats, venue, remarks, route;




    public Puv(){

    }

    public Puv(String id, String platenumber, String category, String date, String start, String end, String venue, String availableseats, String remarks) {
        this.id = id;
        this.platenumber = platenumber;
        this.category = category;
        this.date = date;
        this.start = start;
        this.end = end;
        this.venue = venue;
        this.availableseats = availableseats;
        this.remarks = remarks;
        //this.route = route;
    }

    public Puv(String platenumber, String category, String date, String start, String end, String venue, String availableseats, String remarks) {
        this.platenumber = platenumber;
        this.category = category;
        this.date = date;
        this.start = start;
        this.end = end;
        this.venue = venue;
        this.availableseats = availableseats;
        this.remarks = remarks;
        //this.route = route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAvailableseats() {
        return availableseats;
    }

    public void setAvailableseats(String availableseats) {
        this.availableseats = availableseats;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
