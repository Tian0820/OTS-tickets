package OTS.tickets.OTSserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue
    private Integer id;

    private String area;

    private Integer row;

    private Integer col;

    /**
     * 是否已卖出，已卖出为0，未卖出为1
     */
    private Integer available;

    private Double price;

    public Seat() {
    }

    public Seat(String area, Integer row, Integer col, Integer available, Double price) {
        this.area = area;
        this.row = row;
        this.col = col;
        this.available = available;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
