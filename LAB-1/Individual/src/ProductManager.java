import java.util.Arrays;

class Product
{
    private String name;
    private String manufacturer;
    private int quantity;
    private double price;

    public Product(String name, String manufacturer, int quantity, double price)
    {
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String toString()
    {
        return "Продукт{" +
                "назва='" + name + '\'' +
                ", виробник='" + manufacturer + '\'' +
                ", кількість=" + quantity +
                ", ціна=" + price +
                '}';
    }
}

public class ProductManager
{
    private Product[] products;

    public ProductManager(Product[] products)
    {
        this.products = products;
    }

    public Product[] getProducts()
    {
        return products;
    }

    public Product findProductByName(String name)
    {
        for (int i = 0; i < products.length; i++)
        {
            Product product = products[i];
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }

        return null;
    }

    public Product[] findProductsByPriceBelowAverage()
    {
        double totalPrice = 0;
        for (int i = 0; i < products.length; i++)
        {
            totalPrice += products[i].getPrice();
        }

        double averagePrice = totalPrice / products.length;

        int count = 0;
        for (Product product : products)
        {
            if (product.getPrice() < averagePrice)
            {
                count++;
            }
        }

        if (count > 0)
        {
            Product[] cheaperProducts = new Product[count];
            int index = 0;
            for (Product product : products) {
                if (product.getPrice() < averagePrice)
                {
                    cheaperProducts[index] = product;
                    index++;
                }
            }
            return cheaperProducts;
        }
        else
        {
            return null;
        }
    }

    public void sortByDescendingPrice() {
        Arrays.sort(products, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
    }

    public Product findProductWithMaxQuantity()
    {
        if (products.length == 0)
        {
            return null;
        }

        Product maxQuantityProduct = products[0];
        for (Product product : products)
        {
            if (product.getQuantity() > maxQuantityProduct.getQuantity())
            {
                maxQuantityProduct = product;
            }
        }
        return maxQuantityProduct;
    }

    public double calculateAveragePrice()
    {
        double totalPrice = 0;
        for (Product product : products)
        {
            totalPrice += product.getPrice();
        }
        return totalPrice / products.length;
    }

    public static void main(String[] args)
    {
        Product[] products = {
                new Product("Продукт1", "Виробник1", 10, 15.99),
                new Product("Продукт2", "Виробник2", 20, 25.99),
                new Product("Продукт3", "Виробник3", 30, 35.49)
        };

        ProductManager manager = new ProductManager(products);

        Product maxQuantityProduct = manager.findProductWithMaxQuantity();
        System.out.println("Продукт з максимальною кількістю: " + maxQuantityProduct);

        double averagePrice = manager.calculateAveragePrice();
        System.out.println("Середня ціна: " + averagePrice);

        Product[] cheaperProducts = manager.findProductsByPriceBelowAverage();
        if (cheaperProducts != null)
        {
            System.out.println("Товари з цінами нижче середнього: ");
            for (Product product : cheaperProducts)
            {
                System.out.println(product);
            }
        }
        else
        {
            System.out.println("Немає товарів з ціною нижче середньої.");
        }

        manager.sortByDescendingPrice();
        System.out.println("Товари відсортовані за спаданням ціни:");
        for (Product product : products)
        {
            System.out.println(product);
        }

        String productNameToEdit = "Продукт1";
        Product productToEdit = manager.findProductByName(productNameToEdit);
        if (productToEdit != null)
        {
            productToEdit.setPrice(29.99);
            System.out.println("Відредагований продукт: " + productToEdit);
        }
        else
        {
            System.out.println("Товар не знайдено.");
        }
    }
}
