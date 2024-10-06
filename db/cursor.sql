

begin;

declare curs_products scroll CURSOR FOR select * from products p;

move forward 20 from curs_products;

move backward 5 from curs_products;

move backward 8 from curs_products;

move backward 5 from curs_products;

fetch prior from curs_products;

commit;










