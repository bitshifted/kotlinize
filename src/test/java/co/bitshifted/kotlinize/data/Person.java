package co.bitshifted.kotlinize.data;

public record Person(String firstName, String lastName, Address homeAddress, Address workAddress) {
}
