package org.example.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle
{
    private String ID;
    private String category;
    private String brand;
    private String model;
    private int year;
    private String plate;

    @Builder.Default
    private Map<String, Object> attributes = Map.of();

    public Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    public void addAttribute(String key, Object value)
    {
        attributes.put(key, value);
    }

    public void removeAttribute(String key)
    {
        attributes.remove(key);
    }
}
