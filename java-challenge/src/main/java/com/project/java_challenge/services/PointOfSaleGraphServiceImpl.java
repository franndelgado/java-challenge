package com.project.java_challenge.services;

import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.exceptions.MinimumCostPathException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.utils.Connection;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PointOfSaleGraphServiceImpl implements PointOfSaleGraphService {

    private final PointOfSaleRepository pointOfSaleRepository;
    private final PointOfSaleCostRepository pointOfSaleCostRepository;

    public PointOfSaleGraphServiceImpl(PointOfSaleRepository pointOfSaleRepository,
                                       PointOfSaleCostRepository pointOfSaleCostRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
        this.pointOfSaleCostRepository = pointOfSaleCostRepository;
    }

    @Override
    public MinimumCostPathResponseDTO getMinimumCostPath(int idA, int idB) throws MinimumCostPathException {
        Map<Integer, List<Connection>> graph = buildGraph();

        // Validar que los puntos de venta existan en el grafo
        if (!graph.containsKey(idA) || !graph.containsKey(idB)) {
            throw new MinimumCostPathException("One or both points of sale do not exist.");
        }

        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[]{idA, 0});
        distances.put(idA, 0);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int cumulativeCost = current[1];

            if (visited.contains(node)) continue;
            visited.add(node);

            for (Connection connection : graph.getOrDefault(node, new ArrayList<>())) {
                int neighbour = connection.getDestination();
                int newCost = cumulativeCost + connection.getCost();

                if (newCost < distances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    distances.put(neighbour, newCost);
                    previous.put(neighbour, node);
                    queue.add(new int[]{neighbour, newCost});
                }
            }
        }

        if (!distances.containsKey(idB)) {
            throw new RuntimeException("No path was found between the points of sale " + idA + " and " + idB + ".");
        }

        // Reconstruir el camino
        LinkedList<String> path = new LinkedList<>();
        int actual = idB;
        while (actual != idA) {
            path.addFirst(getPointOfSaleName(actual));
            actual = previous.get(actual);
        }
        path.addFirst(getPointOfSaleName(idA));

        return new MinimumCostPathResponseDTO(distances.get(idB), path);
    }

    private String getPointOfSaleName(int id) {
        return pointOfSaleRepository.findById(id)
                .map(PointOfSale::getName)
                .orElse("Unknown");
    }

    private Map<Integer, List<Connection>> buildGraph() {
        Map<Integer, List<Connection>> graph = new HashMap<>();
        List<PointOfSaleCost> costs = pointOfSaleCostRepository.findAll();

        for (PointOfSaleCost cost : costs) {
            // Validar que idA y idB no sean null
            if (cost.getIdA() == null || cost.getIdB() == null) {
                continue; // Ignorar este registro
            }

            // Agregar conexiones al grafo
            graph.computeIfAbsent(cost.getIdA(), k -> new ArrayList<>())
                    .add(new Connection(cost.getIdB(), cost.getCost()));
            graph.computeIfAbsent(cost.getIdB(), k -> new ArrayList<>())
                    .add(new Connection(cost.getIdA(), cost.getCost()));
        }

        // Imprimir el grafo para depuración
        return graph;
    }
}