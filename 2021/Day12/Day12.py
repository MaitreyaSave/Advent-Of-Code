import collections


def dfs(node, seen, part2):
    if node == 'end':
        return 1
    if node.lower() == node:
        if part2:
            seen = seen + [node]
        else:
            seen = seen.union([node])
    count = 0
    for destination in graph[node]:
        if part2:
            if destination not in seen or len(seen) == len(set(seen)) and destination != 'start':
                count += dfs(destination, seen, part2)
        elif destination not in seen:
                count += dfs(destination, seen, part2)
    return count


with open("2021/Day12/input.txt") as file:
    lines = file.readlines()
    lines = [line.rstrip() for line in lines]

    graph = collections.defaultdict(set)
    for line in lines:
        x, y = line.split('-')
        graph[x].add(y)
        graph[y].add(x)

    seen = set()
    print("1: " + str(dfs('start', seen, False)))
    seen = []
    print("2: " + str(dfs('start', seen, True)))