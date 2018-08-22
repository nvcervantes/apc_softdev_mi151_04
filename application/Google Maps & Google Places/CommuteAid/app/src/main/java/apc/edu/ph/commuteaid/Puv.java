package apc.edu.ph.commuteaid;



public class Puv {




    public String id, title, category,date, start, end, speaker, venue, agenda;




    public Puv(){

    }

    public Puv(String id, String title, String category, String date, String start, String end, String venue, String speaker, String agenda) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.start = start;
        this.end = end;
        this.venue = venue;
        this.speaker = speaker;
        this.agenda = agenda;
    }

    public Puv(String title, String category, String date, String start, String end, String venue, String speaker, String agenda) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.start = start;
        this.end = end;
        this.venue = venue;
        this.speaker = speaker;
        this.agenda = agenda;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
