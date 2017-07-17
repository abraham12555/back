/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

/**
 *
 * @author Becario04
 */
class Pager {

    int page
    long totalRows
    int rowsPerPage

    Pager(){}

    public long getTotalPages() {
        long totalPages
        totalPages = totalRows / rowsPerPage
        if (totalRows % rowsPerPage != 0) {
            totalPages++
        }
        if (totalRows == 0) {
            totalPages = 0
        }
        return totalPages
    }

    public int getFirstRow() {        
        int first
        first = this.page - 1
        first = first * this.rowsPerPage
        return first
    }
}